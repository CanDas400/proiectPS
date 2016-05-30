package GUI;

import java.awt.EventQueue;


import BusinessLogic.UserManager;
import Models.User;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeLogin {

	public JFrame frame;
	private JTextField usernameField;
	private JTextField passwordField;
	private JLabel background;
	private UserManager uMan;
	private JButton btnForgetPassword;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeLogin window = new HomeLogin();
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
	public HomeLogin() {
		initialize();
		uMan = new UserManager();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 722, 254);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setBounds(146, 39, 190, 25);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(146, 86, 190, 25);
		frame.getContentPane().add(passwordField);
		passwordField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String uname = usernameField.getText();
			    String pword = passwordField.getText();
				
			    User celLogat = new User();
			    User celLogat1 = new User();
			    celLogat1=uMan.login(uname);
			    celLogat=uMan.login(uname, pword);
			    if(celLogat1 ==null){
			    	JOptionPane.showMessageDialog(null, "Nu exista user");
			    	usernameField.setText("");
			    	passwordField.setText("");
			    }
			    else{
			    if(celLogat == null){
			    	JOptionPane.showMessageDialog(null, "Parola gresita");
			    	passwordField.setText("");
			    	btnForgetPassword.setVisible(true);
			    	}
			    else if(celLogat.getType().equals("ADMIN")){   
			    	frame.setVisible(false);
			    	AdminLogin adm = new AdminLogin();
			    	adm.frame.setVisible(true);	
			    }
			    else if(celLogat.getType().equals("USER"))
			    {
			    	frame.setVisible(false);
			    	UserLogin usr = new UserLogin();
			    	usr.frame.setVisible(true);
			    }	
			    }
			}
		});
		btnLogin.setBounds(240, 136, 96, 33);
		frame.getContentPane().add(btnLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setBounds(40, 36, 96, 25);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(40, 83, 96, 25);
		frame.getContentPane().add(lblPassword);
		
		btnForgetPassword = new JButton("Forget Password");
		btnForgetPassword.setVisible(false);
		btnForgetPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JTextField userName = new JTextField();
				JTextField passwordNew = new JTextField();
					
				userName.setText(usernameField.getText());
				userName.setEditable(false);
				
				passwordNew.setText(randomstring());
				
				Object[] message = {
					    "Username:", userName,
					    "PasswordNew:", passwordNew,
					  };
					int option = JOptionPane.showConfirmDialog(null, message, "Parola noua", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
								
						uMan.passwordChange(userName.getText(), passwordNew.getText());		
					}
					
			}
		});
		btnForgetPassword.setBounds(89, 136, 141, 33);
		frame.getContentPane().add(btnForgetPassword);
		
		
		background = new JLabel(new ImageIcon(getClass().getResource("/GUI/logoChimdet.png")));
		background.setBounds(371, 26, 306, 134);
		frame.getContentPane().add(background);
	}

    @SuppressWarnings("deprecation")
	public static String randomstring(int lo, int hi){  
            int n = rand(lo, hi);  
            byte b[] = new byte[n];  
            for (int i = 0; i < n; i++)  
                    b[i] = (byte)rand('a', 'z');  
            return new String(b, 0);  
    }  

    private static int rand(int lo, int hi){  
                java.util.Random rn = new java.util.Random();  
            int n = hi - lo + 1;  
            int i = rn.nextInt(n);  
            if (i < 0)  
                    i = -i;  
            return lo + i;  
    }  

    public static String randomstring(){  
            return randomstring(5, 20);  
    } 
}
