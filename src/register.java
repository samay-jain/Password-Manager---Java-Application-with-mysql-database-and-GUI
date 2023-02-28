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

public class register {

	private JFrame frame;
	private JTextField email;
	private JPasswordField cpass;
	private JTextField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					register window = new register();
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
	DBCONNECT db = new DBCONNECT();
	public register() {
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
		frame.setBounds(100, 100, 1059, 657);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRegister = new JLabel("Register new user");
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblRegister.setBounds(358, 18, 334, 78);
		frame.getContentPane().add(lblRegister);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(152, 106, 745, 436);
		frame.getContentPane().add(panel);
		
		JLabel lblUsername = new JLabel("Email Address");
		lblUsername.setFont(new Font("Gadugi", Font.BOLD, 28));
		lblUsername.setBounds(48, 108, 232, 40);
		panel.add(lblUsername);
		
		email = new JTextField();
		email.setBackground(new Color(204, 204, 255));
		email.setFont(new Font("Tahoma", Font.PLAIN, 20));
		email.setColumns(10);
		email.setBounds(313, 114, 259, 37);
		panel.add(email);
		
		JLabel lblCreatePassword = new JLabel("Create Password");
		lblCreatePassword.setFont(new Font("Gadugi", Font.BOLD, 28));
		lblCreatePassword.setBounds(48, 185, 232, 40);
		panel.add(lblCreatePassword);
		
		JLabel lblEnterTheFollowing = new JLabel("Enter the following details");
		lblEnterTheFollowing.setFont(new Font("Futurist", Font.PLAIN, 28));
		lblEnterTheFollowing.setBounds(195, 24, 377, 40);
		panel.add(lblEnterTheFollowing);
		
		JButton btnlogin = new JButton("Register");
		btnlogin.setBackground(new Color(255, 204, 204));
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String mail = email.getText();
				String password = pass.getText();
				String cp = cpass.getText();
				if(mail.isEmpty() || password.isEmpty() || cp.isEmpty())
					JOptionPane.showMessageDialog(null, "Please enter all required details!");
				else
				{
					if(!password.equals(cp))
					{
						JOptionPane.showMessageDialog(null, "Password and Confirmed Password is Different!");
						pass.setText("");
						cpass.setText("");
					}
					else
					{
						int result = db.register(mail, password);
						if(result==-1)
							JOptionPane.showMessageDialog(null, "Registration failed due to Technical Problem!");
						else if(result==0)
						{
							JOptionPane.showMessageDialog(null, "Email is already registered!");
							email.setText("");
							pass.setText("");
							cpass.setText("");
						}
						else if(result==1)
						{
							frame.setVisible(false);
							Login.main(null);
							JOptionPane.showMessageDialog(null, "Registration Successfull!");
						}
					}
				}
			}
		});
		btnlogin.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnlogin.setBounds(273, 360, 146, 47);
		panel.add(btnlogin);
		
		JLabel lblPassword_1 = new JLabel("Confirm Password");
		lblPassword_1.setFont(new Font("Gadugi", Font.BOLD, 28));
		lblPassword_1.setBounds(48, 267, 255, 40);
		panel.add(lblPassword_1);
		
		cpass = new JPasswordField();
		cpass.setBackground(new Color(204, 204, 255));
		cpass.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cpass.setBounds(313, 273, 259, 38);
		panel.add(cpass);
		
		pass = new JTextField();
		pass.setBackground(new Color(204, 204, 255));
		pass.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pass.setColumns(10);
		pass.setBounds(313, 191, 259, 37);
		panel.add(pass);
		
		JButton btnReturn = new JButton("return");
		btnReturn.setBackground(new Color(255, 204, 204));
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				Login.main(null);
			}
		});
		btnReturn.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnReturn.setBounds(440, 564, 117, 31);
		frame.getContentPane().add(btnReturn);
	}
}
