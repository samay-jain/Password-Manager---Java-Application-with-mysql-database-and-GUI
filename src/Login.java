import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField mail;
	private JPasswordField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public static String email="";
	public static String result="";
	DBCONNECT db = new DBCONNECT();
	public Login() {
		db.connect();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 153, 204));
		frame.setResizable(false);
		frame.setBounds(100, 100, 1065, 672);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("Login for user");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblLogin.setBounds(372, 27, 296, 78);
		frame.getContentPane().add(lblLogin);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(191, 137, 646, 360);
		frame.getContentPane().add(panel);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Gadugi", Font.BOLD, 28));
		lblEmail.setBounds(48, 108, 198, 40);
		panel.add(lblEmail);
		
		mail = new JTextField();
		mail.setBackground(new Color(204, 204, 255));
		mail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		mail.setColumns(10);
		mail.setBounds(256, 114, 259, 37);
		panel.add(mail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Gadugi", Font.BOLD, 28));
		lblPassword.setBounds(48, 191, 168, 40);
		panel.add(lblPassword);
		
		JLabel lblEnterTheFollowing = new JLabel("Enter the following details");
		lblEnterTheFollowing.setFont(new Font("Futurist", Font.PLAIN, 28));
		lblEnterTheFollowing.setBounds(127, 21, 377, 40);
		panel.add(lblEnterTheFollowing);
		
		JButton btnlogin = new JButton("login");
		btnlogin.setBackground(new Color(255, 204, 204));
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				email = mail.getText();
				String password = pass.getText();
				if(email.isEmpty() || password.isEmpty())
					JOptionPane.showMessageDialog(null, "Please enter all details");
				else
				{
					result = db.checkCredentials(email, password);
					if(result=="false")
					{
						JOptionPane.showMessageDialog(null, "Invalid email or password");
						pass.setText("");
					}
					else
					{
						frame.setVisible(false);
						homepage.main(null);
					}
				}
			}
		});
		btnlogin.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnlogin.setBounds(277, 290, 85, 40);
		panel.add(btnlogin);
		
		pass = new JPasswordField();
		pass.setBackground(new Color(204, 204, 255));
		pass.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pass.setBounds(256, 197, 259, 38);
		panel.add(pass);
		
		JButton btnRegister = new JButton("register");
		btnRegister.setBackground(new Color(255, 204, 204));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				register.main(null);
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRegister.setBounds(454, 544, 117, 40);
		frame.getContentPane().add(btnRegister);
	}
	public static String getEmail()
	{
		return email;
	}
	public static String getID()
	{
		return result;
	}
}
