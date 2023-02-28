import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class homepage {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					homepage window = new homepage();
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

	public homepage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 153, 204));
		frame.setResizable(false);
		frame.setBounds(100, 100, 802, 533);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblHomePage = new JLabel("Home Page");
		lblHomePage.setHorizontalAlignment(SwingConstants.CENTER);
		lblHomePage.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblHomePage.setBounds(226, 10, 334, 78);
		frame.getContentPane().add(lblHomePage);
		
		JPanel panel = new JPanel();
		panel.setBounds(143, 98, 501, 304);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton add = new JButton("Edit Credentials");
		add.setBackground(new Color(255, 204, 204));
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				editcredentials.main(null);
			}
		});
		add.setFont(new Font("Tahoma", Font.BOLD, 18));
		add.setBounds(154, 66, 195, 50);
		panel.add(add);
		
		JButton view = new JButton("View Credentials");
		view.setBackground(new Color(255, 204, 204));
		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				showpassword.main(null);
			}
		});
		view.setFont(new Font("Tahoma", Font.BOLD, 18));
		view.setBounds(154, 177, 195, 50);
		panel.add(view);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.setBackground(new Color(255, 204, 204));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				Login.main(null);
			}
		});
		btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnLogOut.setBounds(340, 433, 108, 50);
		frame.getContentPane().add(btnLogOut);
	}

}
