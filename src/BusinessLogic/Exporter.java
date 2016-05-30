package BusinessLogic;

import java.util.List;

import javax.swing.JTable;

import Models.Produs;


public interface Exporter {

	public void export(JTable tableProdus,List<Produs> listaProduse);
}
