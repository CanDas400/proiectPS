package DataLayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Models.Comanda;
import Models.Produs;

public class ComandaDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String FILE = "detergenti.dat";
	
	private Comanda comand ;
	private List<Produs> listaProduse;
	
	public ComandaDAO(){

	
		comand = new Comanda();
		listaProduse = new ArrayList<Produs>();
		comand.setListaProduse(listaProduse);
		readProduse();
	}

	public void addProdus(Produs p){
		
		listaProduse = comand.getListaProduse();
		listaProduse.add(p);
		comand.setListaProduse(listaProduse);
		writeProduse();
	}
	
	
	public Produs search(int id) {
		// TODO Auto-generated method stub
		
		listaProduse = comand.getListaProduse();
		
		for (Produs p : listaProduse) {
			if (p.getId()==id) {
				return p;
			}
		}
		return null;
	}
	
	
	public void removeProdus(Produs p){
		
		listaProduse = comand.getListaProduse();
		
		for (int i=listaProduse.size()-1; i> -1; i--) {
		    if (listaProduse.get(i).getNume().equals(p.getNume()) && listaProduse.get(i).getCantitate()==p.getCantitate()){
		    	listaProduse.remove(i);
		    }
		   }
		
		comand.setListaProduse(listaProduse);
		writeProduse();
	}
	
	
	@SuppressWarnings("unchecked")
	public void readProduse() {
		ObjectInputStream in = null;
				try {
					in = new ObjectInputStream(new FileInputStream(FILE));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 try {
			comand.setListaProduse((List<Produs>) in.readObject());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	public void writeProduse() {
		// TODO Auto-generated method stub
		ObjectOutputStream out = null;
			try {
				out = new ObjectOutputStream(new FileOutputStream(FILE));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				out.writeObject(comand.getListaProduse());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public List<Produs> returnList(){
		return comand.getListaProduse();
	}

}
