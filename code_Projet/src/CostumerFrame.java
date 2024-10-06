import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CostumerFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private Account accGlobal;
	private JRadioButton rdbtnNewRadioButton_1;
	/**
	 * Launch the application.
	 */
	public void main(List<Account> accounts) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CostumerFrame frame = new CostumerFrame(accounts);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CostumerFrame(List<Account> accounts) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Account Transactions");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(286, 20, 166, 38);
		contentPane.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Statement", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(338, 68, 456, 300);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
		textArea.setEditable(false);
		textArea.setBounds(10, 21, 436, 268);
		panel_1.add(textArea);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Account", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 69, 318, 299);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("No");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 40, 62, 25);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(82, 42, 95, 23);
		panel.add(textField);
		textField.setColumns(10);
		
		
		JButton btnNewButton = new JButton("Details");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String No=textField.getText();
				for(Account acc : accounts) {
					if(acc.getNumber().equals(No)) {
						accGlobal =acc;
						textField_1.setText(acc.getName());
						textField_2.setText(""+acc.getBalance());
						textArea.setText(acc.bankStatement(LocalDate.MIN));
						break;
					}
				}
			}
		});
		btnNewButton.setBounds(200, 42, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(26, 93, 79, 31);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Balance");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(26, 135, 79, 31);
		panel.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		textField_1.setBackground(UIManager.getColor("Button.background"));
		textField_1.setBounds(115, 99, 135, 25);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBackground(UIManager.getColor("Button.background"));
		textField_2.setBorder(new EmptyBorder(0, 0, 0, 0));
		textField_2.setBounds(114, 139, 136, 25);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Make Operation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 177, 298, 110);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		textField_3 = new JTextField();
		textField_3.setBounds(20, 59, 140, 28);
		panel_2.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Amount");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(10, 34, 77, 21);
		panel_2.add(lblNewLabel_4);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Withdraw");
		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Double amount =Double.parseDouble(textField_3.getText());
				if(amount<=0) {return;}
				LocalDate date = LocalDate.now();
				try {
					accGlobal.setBalance(accGlobal.getBalance() - amount);
					accGlobal.operations.add(new Operation("WITHDRAW",amount,date));
					textField_2.setText(""+accGlobal.getBalance());
					textArea.setText(accGlobal.bankStatement(LocalDate.MIN)); 
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		rdbtnNewRadioButton.setBounds(184, 62, 109, 23);
		panel_2.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("Deposit");
		rdbtnNewRadioButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Double amount =Double.parseDouble(textField_3.getText());
				if(amount<=0) {return;}
				LocalDate date = LocalDate.now();
				try {
					accGlobal.setBalance(accGlobal.getBalance() + amount);
					accGlobal.operations.add(new Operation("DEPOSIT",amount,date));
					textField_2.setText(""+accGlobal.getBalance());
					textArea.setText(accGlobal.bankStatement(LocalDate.MIN));
				} catch (Exception e1) {
					e1.printStackTrace();
				}			
			}
		});
		rdbtnNewRadioButton_1.setBounds(184, 34, 109, 23);
		panel_2.add(rdbtnNewRadioButton_1);	
	}
}
