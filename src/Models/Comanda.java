package Models;

import java.util.ArrayList;
import java.util.List;



public class Comanda {
	
	private List<Produs> listaProduse;
	
	public Comanda(){
		setListaProduse(new ArrayList<Produs>());
	}

	public List<Produs> getListaProduse() {
		return listaProduse;
	}

	public void setListaProduse(List<Produs> listaProduse) {
		this.listaProduse = listaProduse;
	}


}
