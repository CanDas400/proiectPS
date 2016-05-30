package Models;

public class User {
	

	private String nume;
	private String username;
	private String password;
	private String type;
	
	
	public User(String nume,String username,String password,String type){
		this.nume = nume;
		this.username = username;
		this.password = password;
		this.type = type;
		
	}

	public User(){
		
	}

	public String getNume() {
		return nume;
	}


	public void setNume(String nume) {
		this.nume = nume;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
