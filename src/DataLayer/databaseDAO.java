package DataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseDAO {
	
	private String url = "jdbc:mysql://localhost/chimdet";
	private	String uid = "root";
	private	String pw = null;
	private Connection con;
	
	public databaseDAO() {
		init();
	}
	
	public Connection getConnection(){
		return this.con;
	}
	
	private void init()
	{
		// Initialize your application
		// Register the MySQL driver and make a connection
		
		try {  // Load driver class
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (java.lang.ClassNotFoundException e) {
			System.err.println("ClassNotFoundException: " +e);
		}
        con = null;
		try {
			con = DriverManager.getConnection(url, uid, pw);
		}
		catch (SQLException ex) {
			System.err.println("SQLException: " + ex);
			System.exit(1);
		}
	}

	
	
	

}
