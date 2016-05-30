package GUI;

import java.awt.Component;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import BusinessLogic.AdminManager;
import BusinessLogic.UserManager;

import Models.Produs;
import Models.User;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminLogin {

	JFrame frame;
	private JScrollPane scrollPane;
	private DefaultTableModel modelProdus;
	private JTable tableProdus;
	private JButton buttonAdd;
	private JButton buttonStg;
	private JButton button;
	private UserManager uMan;
	private AdminManager aMan;

	/**
	 * Create the application.
	 */
	public AdminLogin() {
		uMan = new UserManager();
		aMan = new AdminManager();
		tableInit();
		
		initialize();
		refreshTable();
	
	}
	
	private void refreshTable(){
		modelProdus.setRowCount(0);
	
		for (Produs p : aMan.listaProduse()) {
			modelProdus.addRow(new Object[] {p.getId(),p.getNume(),p.getCantitate(),
					p.getARAD()>0?p.getARAD():"",p.getCLUJ()>0?p.getCLUJ():"",p.getBRAS()>0?p.getBRAS():""
					,p.getBUCU()>0?p.getBUCU():"",p.getPITE()>0?p.getPITE():"",p.getFALTI()>0?p.getFALTI():""
					,p.getBACAU()>0?p.getBACAU():"",p.getALBA()>0?p.getALBA():"",p.getCONST()>0?p.getCONST():"",
					p.getBIHOR()>0?p.getBIHOR():""
				});
		}
		
	}
	
	private void tableInit(){
		modelProdus = new DefaultTableModel();
		
		
		
		tableProdus = new JTable(modelProdus)
		{
		     //  Place cell in edit mode when it 'gains focus'

		     /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void changeSelection(
		          int row, int column, boolean toggle, boolean extend)
		     {
		          super.changeSelection(row, column, toggle, extend);

		          if (editCellAt(row, column))
		          {
		               Component editor = getEditorComponent();
		               editor.requestFocusInWindow();
//		               ((JTextComponent)editor).selectAll();
		          }
		     }
		};
		
		
		tableProdus.setFont(new Font("Arial",0, 15));
		tableProdus.setRowHeight(19);
		
		modelProdus.addColumn("NR");
		modelProdus.addColumn("NUME");
		modelProdus.addColumn("LITRI");
		modelProdus.addColumn("ARAD");
		modelProdus.addColumn("CLUJ");
		modelProdus.addColumn("BRAS");
		modelProdus.addColumn("BUCU");
		modelProdus.addColumn("PITES");
		modelProdus.addColumn("FALTI");
		modelProdus.addColumn("BACAU");
		modelProdus.addColumn("ALBA");
		modelProdus.addColumn("CONST");
		modelProdus.addColumn("ORADEA");
		
	    TableColumn column;
	    for (int i = 0; i <13; i++) {
	        column = tableProdus.getColumnModel().getColumn(i);
	        if (i == 0) {
	            column.setPreferredWidth(23); //sport column is bigger
	        }
	        else if(i==1){
	        	 column.setPreferredWidth(140);
	        }
	        else if(i==2){
	        	column.setPreferredWidth(10);
	        }
	        else {
	            column.setPreferredWidth(30);
	        }
	    }  
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 948, 562);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 96, 912, 426);
		scrollPane.setViewportView(tableProdus);
		frame.getContentPane().add(scrollPane);
		
		buttonAdd = new JButton("Adauga");
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JTextField produs = new JTextField();
				JTextField cantitate = new JTextField();
				JTextField id = new JTextField();
				
				Object[] message = {
				    "ID:", id,
				    "Produs:", produs,
				    "Cantitate:", cantitate
				};
				String produs1="";
				double cantitate1=0.0;
				int id1=0;
				int option = JOptionPane.showConfirmDialog(null, message, "Date", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					if(produs.getText().length()>0 && cantitate.getText().length()>0 && id.getText().length()>0){
					produs1= produs.getText();
					try {
						cantitate1 = Double.parseDouble(cantitate.getText());
						id1 = Integer.parseInt(id.getText());
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
					//adaugare Produs
					Produs p = new Produs(produs1,cantitate1,id1);
					aMan.adaugaProdus(p);
					
					refreshTable();
					
					JOptionPane.showMessageDialog(null,"Produs: " + produs1 + "\n" +
							"Cantitate: " + cantitate1 + "\n" + 
							"Numar: " + id1);
					}else{
						JOptionPane.showMessageDialog(null, "Completeaza fiecare camp!");
					}	
				}		
			}
				
		});
		buttonAdd.setBounds(20, 28, 117, 40);
		frame.getContentPane().add(buttonAdd);
		
		buttonStg = new JButton("Sterge");
		buttonStg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String numeProdus = (String) modelProdus.getValueAt(tableProdus.getSelectedRow(), 1);
					double cantitate = (double) modelProdus.getValueAt(tableProdus.getSelectedRow(), 2);
					int numar = (int) modelProdus.getValueAt(tableProdus.getSelectedRow(), 0);
					Produs p = new Produs(numeProdus, cantitate, numar);
					
					aMan.removeProdus(p);
				
					refreshTable();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				
			}
		});
		buttonStg.setBounds(147, 28, 105, 40);
		frame.getContentPane().add(buttonStg);
		
		button = new JButton("Angajat Nou");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				JTextField nume = new JTextField();
				JTextField username = new JTextField();
				JTextField parola = new JTextField();

				
				Object[] message = {
					    "Nume:", nume,
					    "Username:", username,
					    "Password:", parola
					  };
					int option = JOptionPane.showConfirmDialog(null, message, "Date", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						User usr = new User();
						usr.setNume(nume.getText());
						usr.setPassword(parola.getText());
						usr.setUsername(username.getText());
						
						uMan.createUserAccount(usr);
						
						JOptionPane.showMessageDialog(null, "S-a creat cu succes!");
						
					}
				
				
				
				
			}
		});
		button.setBounds(262, 28, 152, 40);
		frame.getContentPane().add(button);
		
	}
//metoda pentru a nu se putea introduce decat cifre in TextField-----------------------------------------------------
/*	private void metoda(KeyEvent arg0) {
		char c=arg0.getKeyChar();
		if (!( ((c >= '0') && (c <= '9')) || (c=='.'))) {
		        arg0.consume();
		}	
}
*/

}
