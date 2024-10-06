import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public void main(Bank bank) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFrame frame = new AdminFrame(bank);
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
	public AdminFrame(Bank bank) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 838, 501);
		JPanel contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "New Account", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 65, 268, 392);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New Name");
		lblNewLabel.setBounds(21, 71, 84, 30);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(115, 71, 137, 30);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("New Balance");
		lblNewLabel_1.setBounds(21, 153, 84, 30);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(115, 153, 137, 30);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Account");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(24, 230, 93, 30);
		panel.add(lblNewLabel_2);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox.setBounds(104, 234, 118, 26);
		panel.add(comboBox);
		comboBox.addItem("Current");
		comboBox.addItem("Saving");
		
		
		JLabel lblNewLabel_3 = new JLabel(bank.getName());
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(325, 22, 135, 32);
		contentPane.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Accounts List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(278, 65, 534, 388);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 21, 514, 356);
		panel_1.add(textArea);
		String strAccounts="";
		for(Account account : Bank.accounts) {
			strAccounts = strAccounts +account.toString() + "\n";
		}
		textArea.setText(strAccounts);
		
		
		JButton btnNewButton = new JButton("Create New Account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Name = textField.getText();
				double Balance;
				try {
				   Balance = Double.parseDouble(textField_1.getText());
				}catch(Exception e1) {
					return;
				}
				String Type = (String) comboBox.getSelectedItem();
				if(Type.equals("Current")) {
					Bank.accounts.add(new CurrentAccount(Name,Balance,1000));
				}else {
					Bank.accounts.add(new SavingAccount(Name, Balance, 0.05));
				}
				String strAccounts="";
				for(Account account : Bank.accounts) {
					strAccounts = strAccounts +account.toString() + "\n";
				}
				textArea.setText(strAccounts);
			}
		});
		btnNewButton.setBounds(21, 319, 223, 39);
		panel.add(btnNewButton);
	}
}
