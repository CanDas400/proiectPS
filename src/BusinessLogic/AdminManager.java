package BusinessLogic;

import java.util.List;

import DataLayer.ComandaDAO;
import Models.Produs;

public class AdminManager {
	
	ComandaDAO comand;
	
	public AdminManager(){
		comand = new ComandaDAO();
	}
	
	public void adaugaProdus(Produs p){
		
		comand.addProdus(p);
		
	}
	
	public void removeProdus(Produs p){
		
		comand.removeProdus(p);
	}
	
	public List<Produs> listaProduse(){
		
		return comand.returnList();
	}
	

}
