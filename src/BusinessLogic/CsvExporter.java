package BusinessLogic;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JTable;


import Models.Produs;

public class CsvExporter implements Exporter{

	@Override
	public void export(JTable tableProdus, List<Produs> listaProduse) {
		// TODO Auto-generated method stub
		
		try
		{
		
		    FileWriter writer = new FileWriter("BileteTeatru.csv");
			 
		    for(Produs t: listaProduse){
		     writer.append("Id: ");
		     writer.append(String.valueOf(t.getId()));
		     writer.append(" Nume: ");
		     writer.append(t.getNume());
		     writer.append(" Cantitate: ");
		     writer.append(String.valueOf(t.getCantitate()));
		     writer.append(" Arad: ");
		     writer.append(String.valueOf(t.getARAD()));
		     writer.append(" Cluj: ");
		     writer.append(String.valueOf(t.getCLUJ()));
		     writer.append(" Brasov: ");
		     writer.append(String.valueOf(t.getBRAS()));
		     writer.append(" Bucuresti: ");
		     writer.append(String.valueOf(t.getBUCU()));
		     writer.append(" Pitesti: ");
		     writer.append(String.valueOf(t.getPITE()));
		     writer.append(" Falticeni: ");
		     writer.append(String.valueOf(t.getFALTI()));
		     writer.append(" Bacau: ");
		     writer.append(String.valueOf(t.getBACAU()));
		     writer.append(" Alba: ");
		     writer.append(String.valueOf(t.getALBA()));
		     writer.append(" Constanta: ");
		     writer.append(String.valueOf(t.getCONST()));
		     writer.append(" Oradea: ");
		     writer.append(String.valueOf(t.getBIHOR()));
		     writer.append("\n");
		    }
				
		    //generate whatever data you want
				
		    writer.flush();
		    writer.close();
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		} 
		
		
		
		
	}

}
