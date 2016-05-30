package GUI;


import java.awt.Component;
import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


import BusinessLogic.ProdusManager;

import Models.Produs;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.awt.event.ActionEvent;

public class UserLogin implements DropTargetListener{

	JFrame frame;
	private JScrollPane scrollPane;
	private DefaultTableModel modelProdus;
	private JTable tableProdus;
	private ProdusManager pMan;
	@SuppressWarnings("unused")
	private DropTarget dt;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public UserLogin() {
		pMan = new ProdusManager();
		tableInit();
		dt = new DropTarget(tableProdus,this);
		initialize();
		refreshTable();
	}
	
	
	private void refreshTable(){
		modelProdus.setRowCount(0);
	
		for (Produs p : pMan.listaProduse()) {
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
		
		JButton butKg = new JButton("KG/ORAS");
		butKg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				
				
				double suma5L=0.0,suma1L=0.0,suma075L=0.0,suma05L=0.0,suma20L=0.0,sumaTotal=0.0;
			
				String[] choices = {"ARAD", "CLUJ", "BRASOV","BUCURESTI","ARGES","SUCEAVA","BACAU","ALBA","CONSTANTA","BIHOR"};
			    String input = (String) JOptionPane.showInputDialog(null, "Alege orasul...",
			        "Alegere oras", JOptionPane.QUESTION_MESSAGE, null,                                                                                           
			        choices, 
			        choices[0]); 
				List<Double> lista = pMan.kilogramePerOras(input);
				
				suma05L=lista.get(0);
				suma075L=lista.get(1);
				suma1L=lista.get(2);
				suma5L=lista.get(3);
				suma20L=lista.get(4);
				sumaTotal=lista.get(5);
				
				JOptionPane.showMessageDialog(null, 
						"Numar peturi de 0,5L:   "+ suma05L + "  in total  " + suma05L * 0.5 + "\n"+
						"Numar peturi de 0,75L:   "+ suma075L + "   in total  " + suma075L * 0.75 + "\n"+
						"Numar peturi de 1L:   "+ suma1L+ "   in total  " + suma1L * 1.0 + "\n"+
						"Numar peturi de 5L:   "+ suma5L + "   in total  " + suma5L * 5.0+ "\n"+
						"Numar peturi de 20L:  "+ suma20L + "   in total  " + suma20L * 20.0+ "\n"+
						"TOTAL:" + sumaTotal + " kg"
						);
				}catch(Exception e2){
					
				}
				
			}
		});
		butKg.setBounds(317, 41, 89, 29);
		frame.getContentPane().add(butKg);
		
		JButton butCuratare = new JButton("Curatare");
		butCuratare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				pMan.curatare();	

				refreshTable();	
			}
		});
		butCuratare.setBounds(416, 41, 89, 29);
		frame.getContentPane().add(butCuratare);
		
		JButton butStg = new JButton("Sterge coloana");
		butStg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{	
				String[] choices = {"ARAD", "CLUJ", "BRASOV","BUCURESTI","ARGES","SUCEAVA","BACAU","ALBA","CONSTANTA","BIHOR"};
				    String input = (String) JOptionPane.showInputDialog(null, "Alege orasul...",
				        "Alegere oras", JOptionPane.QUESTION_MESSAGE, null,                                                                                           
				        choices, 
				        choices[0]); 
				    
				    pMan.stergeColoana(input);
				    
			
					refreshTable();	
				}catch(Exception e1){
					
				}
		
			}
		});
		butStg.setBounds(515, 41, 159, 29);
		frame.getContentPane().add(butStg);
		
		JButton butTotal = new JButton("Total");
		butTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double suma5L=0.0,suma1L=0.0,suma075L=0.0,suma05L=0.0,suma20L=0.0,sumaTotal=0.0;
				
				List<Double> lista  = pMan.kilogrameTotal();
				
				suma05L=lista.get(0);
				suma075L=lista.get(1);
				suma1L=lista.get(2);
				suma5L=lista.get(3);
				suma20L=lista.get(4);
				sumaTotal=lista.get(5);
				
				JOptionPane.showMessageDialog(null, 
						"Numar peturi de 0,5L:   "+ suma05L + "  in total  " + suma05L * 0.5 + "\n"+
						"Numar peturi de 0,75L:   "+ suma075L + "   in total  " + suma075L * 0.75 + "\n"+
						"Numar peturi de 1L:   "+ suma1L+ "   in total  " + suma1L * 1.0 + "\n"+
						"Numar peturi de 5L:   "+ suma5L + "   in total  " + suma5L * 5.0+ "\n"+
						"Numar peturi de 20L:  "+ suma20L + "   in total  " + suma20L * 20.0+ "\n"+
						"TOTAL:" + sumaTotal + " kg"
						);
				
			}
		});
		butTotal.setBounds(218, 41, 89, 28);
		frame.getContentPane().add(butTotal);
		
		JButton butListare = new JButton("Listare");
		butListare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				pMan.printeza(tableProdus, pMan.listaProduse());
				
				
			}
		});
		butListare.setBounds(20, 41, 89, 29);
		frame.getContentPane().add(butListare);
		
		JButton butExport = new JButton("Export");
		butExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				pMan.exportaFisier(tableProdus, pMan.listaProduse());
				
				JOptionPane.showMessageDialog(null, "Reusit");
			}
		});
		butExport.setBounds(119, 41, 89, 29);
		frame.getContentPane().add(butExport);
		
		
		
		
	}


	@Override
	public void dragEnter(DropTargetDragEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void dragExit(DropTargetEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void dragOver(DropTargetDragEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void drop(DropTargetDropEvent dtde) {
		// TODO Auto-generated method stub
		
		
			 int coloana=0;
				String[] choices = {"ARAD", "CLUJ", "BRASOV","BUCURESTI","ARGES","SUCEAVA","BACAU","ALBA","CONSTANTA","BIHOR"};
			    String input = (String) JOptionPane.showInputDialog(null, "Alege orasul...",
			        "Alegere oras", JOptionPane.QUESTION_MESSAGE, null,                                                                                           
			        choices, 
			        choices[0]); 
				coloana= pMan.alegereOras(input);
				
				
				 File selectedFile = null;
				 
				dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
		     
				Transferable tr = dtde.getTransferable();
			     DataFlavor[] flavors = tr.getTransferDataFlavors();
			     	for (DataFlavor flavor: flavors) {
			     			try{

			     					if (flavor.isFlavorJavaFileListType()) {
		    	  
			     						@SuppressWarnings("unchecked")
			     						List<File> files = (List<File>) tr.getTransferData(flavor);

			     						// Loop them through
			     						for (File file : files){
			     							selectedFile = file;
			     						}
			     					}
			     			} catch (Exception e) {
			     				// Print out the error stack
			     				//e.printStackTrace();
			     			}
			     	}
		       
		      String str = pMan.adaugaComanda(coloana, selectedFile);
		      
		       if(str.equals("")) {
		    	   JOptionPane.showMessageDialog(null, "Operatiune reusita");
					
				}
		       else{
		    		JOptionPane.showMessageDialog(null, str);
		       }
		       refreshTable();	
	}


	@Override
	public void dropActionChanged(DropTargetDragEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
