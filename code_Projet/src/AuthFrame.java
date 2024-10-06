import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

	
public class AuthFrame extends JFrame {


	    private static final long serialVersionUID = 1L;
		private JPanel contentPane;
		private JTextField textField;
		private JPasswordField passwordField;
		private JTextField textField_1;

		/**
		 * Launch the application.
		 */
		public void main(Bank bank) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						AuthFrame frame = new AuthFrame(bank);
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
		public AuthFrame(Bank bank) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 545, 420);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Authorization", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(22, 31, 478, 231);
			contentPane.add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Login");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNewLabel.setBounds(42, 61, 96, 22);
			panel.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Password");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNewLabel_1.setBounds(42, 94, 85, 28);
			panel.add(lblNewLabel_1);
			
			textField = new JTextField();
			textField.setBounds(148, 60, 180, 25);
			panel.add(textField);
			textField.setColumns(10);
			
			passwordField = new JPasswordField();
			passwordField.setBounds(148, 98, 180, 24);
			panel.add(passwordField);
			
			JButton btnNewButton = new JButton("Ok");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String login = textField.getText();
					String password = new String(passwordField.getPassword());
					if(bank.start(login, password)) {
						textField_1.setText("admin authentification is successful.access granted");
						(new AdminFrame(bank)).main(bank);
						(new CostumerFrame(Bank.accounts)).main(Bank.accounts);
						
						//dispose();
					}else {
						textField_1.setText("admin authentification is unsuccessful.access denied");
					}
					
					
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnNewButton.setBounds(166, 156, 121, 36);
			panel.add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("Cancel");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnNewButton_1.setBounds(320, 156, 121, 36);
			panel.add(btnNewButton_1);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "Status", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(22, 292, 478, 65);
			contentPane.add(panel_1);
			panel_1.setLayout(null);
			
			textField_1 = new JTextField();
			textField_1.setBorder(new EmptyBorder(0, 0, 0, 0));
			textField_1.setBackground(UIManager.getColor("Button.background"));
			textField_1.setBounds(10, 21, 458, 33);
			panel_1.add(textField_1);
			textField_1.setColumns(10);
		}
	}
