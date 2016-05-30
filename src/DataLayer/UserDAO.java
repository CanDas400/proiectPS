package DataLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.User;

public class UserDAO {
	
	 private databaseDAO bazaDate;
	 
	 public UserDAO(){
		 
		 bazaDate = new databaseDAO();
	 }
		
		public List<User> getUseri(){
			
			List<User> listaEv = new ArrayList<User>();
			
			
			Connection c = bazaDate.getConnection();
			
			String sqlSt = "SELECT * FROM user";
			Statement stmt;
			try {
				stmt = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rst;
				rst = stmt.executeQuery(sqlSt);
				while(rst.next())
				{
					User ang = new User();
					
					ang.setNume(rst.getString("Name"));
					ang.setPassword(rst.getString("password"));
					ang.setUsername(rst.getString("userName"));
					ang.setType(rst.getString("type"));
					
					listaEv.add(ang);		
					
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		
			
			return listaEv;
		}
		
		
		public void addUser(User angajat){

		
			Connection c = bazaDate.getConnection();
			
			
			String sqlSt="INSERT INTO user(Name,userName,password,type) VALUES ('"+angajat.getNume()+"','"+angajat.getUsername()+"','"+angajat.getPassword()+"','"+"USER"+"');";
			Statement stmt;
			try {
				stmt = c.createStatement();
			     stmt.executeUpdate(sqlSt);
		
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		public void updatePassword(String username,String password){
	   
			Connection c = bazaDate.getConnection();
			
			
		

			String sqlSt="UPDATE user SET password='"+password+"' WHERE userName='"+username+"';"; 
			Statement stmt;
			try {
				stmt = c.createStatement();
			     stmt.executeUpdate(sqlSt);
		
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}


}
