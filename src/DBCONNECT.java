import java.sql.*;
public class DBCONNECT 
{
	Connection con;
	PreparedStatement stmt;
	ResultSet rs;
	public void connect()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/project";
            String username = "root";
            String password = "";
            con = DriverManager.getConnection(url,username,password);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public String checkCredentials(String email,String password)
	{
		try {
			String query="SELECT password,id FROM logindb where email=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			String pass="";
			String id="";
			while(rs.next())
			{
				pass = rs.getString(1);
				id = rs.getString(2);
			}
			if(password.equals(pass))
				return id;
			else
				return "false";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "false";
		}
	}
	
	public int register(String email,String password)
	{
		try {
			String query = "Select email from logindb";
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				String mail = rs.getString(1);
				if(mail.equals(email))
					return 0;
			}
			query = "INSERT INTO LOGINDB(EMAIL,PASSWORD) values(?,?)";
			stmt = con.prepareStatement(query);
			stmt.setString(1, email);
			stmt.setString(2, password);
			stmt.executeUpdate();
			return 1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}
	
	public boolean saveCredentials(String web,String mail,String pass)
	{
		try {
			String query="Insert into credentials(id,website,email,password) values(?,?,?,?)";
			stmt = con.prepareStatement(query);
			stmt.setString(1, Login.getID());
			stmt.setString(2, web);
			stmt.setString(3, mail);
			stmt.setString(4, pass);
			stmt.executeUpdate();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteCredentials(String id)
	{
		try {
			String query="Delete from CREDENTIALS where CR_ID=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, id);
			stmt.executeUpdate();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateCredentials(String id,String web,String mail,String pass)
	{
		try {
			String query="Update credentials set website=?,email=?,password=? where cr_id=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, web);
			stmt.setString(2, mail);
			stmt.setString(3, pass);
			stmt.setString(4, id);
			stmt.executeUpdate();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
