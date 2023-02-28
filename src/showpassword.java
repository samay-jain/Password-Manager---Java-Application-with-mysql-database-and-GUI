import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class showpassword {

	private JFrame frame;
	private JTable table;
	private JButton btnReturn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					showpassword window = new showpassword();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	DBCONNECT db = new DBCONNECT();
	public void printCredentials()
	{
		try {
			String ID = Login.getID();
			String query = "Select CR_ID as ID,Website as Website_APP,EMAIL as Email_Username,Password from Credentials where ID=?";
			db.stmt = db.con.prepareStatement(query);
			db.stmt.setString(1, ID);
			db.rs = db.stmt.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(db.rs));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Create the application.
	 */
	public showpassword() {
		db.connect();
		initialize();
		printCredentials();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 153, 204));
		frame.setResizable(false);
		frame.setBounds(100, 100, 1058, 648);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(78, 77, 886, 460);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblCredentials = new JLabel("Credentials");
		lblCredentials.setHorizontalAlignment(SwingConstants.CENTER);
		lblCredentials.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblCredentials.setBounds(356, 1, 334, 78);
		frame.getContentPane().add(lblCredentials);
		
		btnReturn = new JButton("return");
		btnReturn.setBackground(new Color(255, 204, 204));
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				homepage.main(null);
			}
		});
		btnReturn.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnReturn.setBounds(462, 547, 104, 50);
		frame.getContentPane().add(btnReturn);
	}
}
