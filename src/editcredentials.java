import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;

public class editcredentials {

	private JFrame frame;
	private JTable table;
	private JTextField website;
	private JTextField email;
	private JTextField password;
	private JTextField crid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					editcredentials window = new editcredentials();
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
	public editcredentials() {
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
		frame.setBounds(100, 100, 1231, 693);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(21, 81, 296, 70);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_3 = new JLabel("Credential ID");
		lblNewLabel_1_3.setBounds(23, 18, 156, 35);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_1.add(lblNewLabel_1_3);
		
		crid = new JTextField();
		crid.setBackground(new Color(204, 204, 255));
		crid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				String id = crid.getText();
				String query = "SELECT website,email,password FROM credentials WHERE CR_ID=? and ID=?";
				try {
					db.stmt = db.con.prepareStatement(query);
					db.stmt.setString(1, id);
					db.stmt.setString(2, Login.getID());
					db.rs = db.stmt.executeQuery();
					if(db.rs.next()==true)
					{
						String web=db.rs.getString(1);
						String mail=db.rs.getString(2);
						String pass=db.rs.getString(3);
						website.setText(web);
						email.setText(mail);
						password.setText(pass);
					}
					else
					{
						clear();
					}
				}
				catch(Exception e3)
				{
					e3.printStackTrace();
				}
			}
		});
		crid.setFont(new Font("Tahoma", Font.PLAIN, 20));
		crid.setColumns(10);
		crid.setBounds(176, 20, 92, 31);
		panel_1.add(crid);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(557, 79, 650, 511);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Edit/Add Credentials", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(21, 175, 521, 236);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("Website/App Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(23, 34, 205, 38);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email/Username");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(23, 95, 205, 38);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Password");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(23, 157, 205, 38);
		panel.add(lblNewLabel_1_2);
		
		website = new JTextField();
		website.setBackground(new Color(204, 204, 255));
		website.setFont(new Font("Tahoma", Font.PLAIN, 19));
		website.setColumns(10);
		website.setBounds(238, 34, 259, 38);
		panel.add(website);
		
		email = new JTextField();
		email.setBackground(new Color(204, 204, 255));
		email.setFont(new Font("Tahoma", Font.PLAIN, 19));
		email.setColumns(10);
		email.setBounds(238, 95, 259, 38);
		panel.add(email);
		
		password = new JTextField();
		password.setBackground(new Color(204, 204, 255));
		password.setFont(new Font("Tahoma", Font.PLAIN, 19));
		password.setColumns(10);
		password.setBounds(238, 157, 259, 38);
		panel.add(password);
		
		JButton btnsave = new JButton("Add");
		btnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String web = website.getText();
				String mail = email.getText();
				String pass = password.getText();
				if(web.isEmpty() || mail.isEmpty() || pass.isEmpty())
					JOptionPane.showMessageDialog(null, "Please fill all details!");
				else
				{
					boolean b = db.saveCredentials(web,mail,pass);
					if(b==true)
					{
						printCredentials();
						JOptionPane.showMessageDialog(null, "Data is added successfully!");
						clear();
					}
					else
					{
						printCredentials();
						JOptionPane.showMessageDialog(null, "Data isn't added due to some Technical Issue!");
						clear();
					}
				}
			}
		});
		btnsave.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnsave.setBackground(new Color(255, 204, 204));
		btnsave.setBounds(41, 443, 102, 38);
		frame.getContentPane().add(btnsave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String id = crid.getText();
				String web = website.getText();
				String mail = email.getText();
				String pass = password.getText();
				if(id.isEmpty())
					JOptionPane.showMessageDialog(null, "Please enter a valid credential ID!");
				else
				{
					boolean b = db.updateCredentials(id,web,mail,pass);
					if(b==true)
					{
						JOptionPane.showMessageDialog(null, "Credential is updated successfully!");
						clear();
						printCredentials();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Data can't be updated due to some Technical Issue!");
						clear();
						printCredentials();
					}
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUpdate.setBackground(new Color(255, 204, 204));
		btnUpdate.setBounds(198, 443, 102, 38);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String id = crid.getText();
				if(id.isEmpty())
					JOptionPane.showMessageDialog(null, "Please enter id to delete the credentials!");
				else
				{
					boolean b=db.deleteCredentials(id);
					if(b==true)
					{
						JOptionPane.showMessageDialog(null, "Credential is Deleted successfully!");
						clear();
						printCredentials();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Unable to delete due to Technical Issue!");
						printCredentials();
						clear();
					}
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDelete.setBackground(new Color(255, 204, 204));
		btnDelete.setBounds(360, 443, 102, 38);
		frame.getContentPane().add(btnDelete);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
				printCredentials();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnClear.setBackground(new Color(255, 204, 204));
		btnClear.setBounds(198, 512, 102, 38);
		frame.getContentPane().add(btnClear);
		
		JButton btnReturn = new JButton("return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				homepage.main(null);
			}
		});
		btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnReturn.setBackground(new Color(255, 204, 204));
		btnReturn.setBounds(771, 608, 102, 38);
		frame.getContentPane().add(btnReturn);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				Login.main(null);
			}
		});
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLogOut.setBackground(new Color(255, 204, 204));
		btnLogOut.setBounds(912, 608, 102, 38);
		frame.getContentPane().add(btnLogOut);
		
		JLabel lblEditCredentials = new JLabel("Edit Credentials");
		lblEditCredentials.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditCredentials.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblEditCredentials.setBounds(429, 0, 334, 78);
		frame.getContentPane().add(lblEditCredentials);
	}
	public void clear()
	{
		crid.setText("");
		website.setText("");
		email.setText("");
		password.setText("");
	}
	public void printCredentials()
	{
		try {
			String ID = Login.getID();
			String query = "Select CR_ID as ID,Website as Website_Application,EMAIL as Email_Username,Password from Credentials where ID=?";
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
}
