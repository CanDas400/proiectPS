package BusinessLogic;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import DataLayer.UserDAO;
import Models.User;
public class UserManager {
	
	private UserDAO lUser;
	
	public UserManager(){
		lUser = new UserDAO();
		
	}
	
	public User login(String username, String password){
		
		User userReturnat = new User();
		userReturnat = null;
		
		List<User> listaUseri = lUser.getUseri();
		
		for(User l:listaUseri){
			
			if(l.getUsername().equals(username)) {
				 if(l.getPassword().equals(getMD5(password))){
				userReturnat = l;
			 break;
				 }
			}
		}
			
		return userReturnat;
	}

	public User login(String username){
		
		User userReturnat = new User();
		userReturnat = null;
		
		List<User> listaUseri = lUser.getUseri();
		
		for(User l:listaUseri){
			
			if(l.getUsername().equals(username)) {
				userReturnat = l;
				break;	 
			}
		}
		return userReturnat;
	}
	
	
	public void passwordChange(String username,String password){
		
				lUser.updatePassword(username, getMD5(password));
		
	}
	public void createUserAccount(User angajat){
		
		angajat.setPassword(getMD5(angajat.getPassword()));
		lUser.addUser(angajat);
	}
	
	public static String getMD5(String input) {
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            byte[] messageDigest = md.digest(input.getBytes());
	            BigInteger number = new BigInteger(1, messageDigest);
	            String hashtext = number.toString(16);
	       
	            while (hashtext.length() < 32) {
	                hashtext = "0" + hashtext;
	            }
	            return hashtext;
	        }
	        catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
	    }


}
