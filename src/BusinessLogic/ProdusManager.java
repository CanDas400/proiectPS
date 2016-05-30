package BusinessLogic;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTable;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import DataLayer.ComandaDAO;
import Models.Produs;

public class ProdusManager {
	
	private ComandaDAO comand;
	
	
	
	public ProdusManager(){
		
		comand = new ComandaDAO();
		
	}
	
	
	public void printeza(JTable tableProdus,List<Produs> listaProduse) {
		
		ExportFactory ex = new ExportFactory();
		
		Exporter inte = ex.getExporter("Print");
		inte.export(tableProdus, listaProduse);
		
	}
	
public void exportaFisier(JTable tableProdus,List<Produs> listaProduse) {
		
		ExportFactory ex = new ExportFactory();
		
		Exporter inte = ex.getExporter("Csv");
		inte.export(tableProdus, listaProduse);
		
	}
	
	public List<Double> kilogrameTotal (){
		
	
		
		List<Double> listaSume = new ArrayList<Double>();
		double suma5L=0.0,suma1L=0.0,suma075L=0.0,suma05L=0.0,suma20L=0.0,sumaTotal=0.0;

		for(Produs p: comand.returnList()) {
			for(int j=3; j<=12; j++){
			if(p.getCantitate()==5.0){
				suma5L+=sumaOras(j,p.getId());
			}
			if(p.getCantitate()==0.75){
				suma075L+=sumaOras(j,p.getId());
			}
			if(p.getCantitate()==1.0){
				suma1L+=sumaOras(j,p.getId());
			}
			if(p.getCantitate()==0.5){
				suma05L+=sumaOras(j,p.getId());
			}
			if(p.getCantitate()==20.0){
				suma20L+=sumaOras(j,p.getId());
			}
			}
	     }
		
		sumaTotal=suma5L*5.0+suma075L*0.75+suma1L*1.0+suma05L*0.5+suma20L*20.0;
		
		listaSume.add(suma05L);	
		listaSume.add(suma075L);	
		listaSume.add(suma1L);	
		listaSume.add(suma5L);	
		listaSume.add(suma20L);	
		listaSume.add(sumaTotal);	
		
		return listaSume;
	}
	
	public List<Double> kilogramePerOras(String input){
		
		
		int coloana=alegereOras(input);
		
		List<Double> listaSume = new ArrayList<Double>();
		double suma5L=0.0;
		double suma075L=0.0;
		double suma1L=0.0;
		double suma05L=0.0;
		double suma20L =0.0;
		double sumaTotal = 0.0;
		
	
		for(Produs p: comand.returnList()) {
			
				if(p.getCantitate()==5.0){
				suma5L+=sumaOras(coloana,p.getId());
				}
				if(p.getCantitate()==0.75){
				suma075L+=sumaOras(coloana,p.getId());
				}
				if(p.getCantitate()==1.0){
				suma1L+=sumaOras(coloana,p.getId());
				}
				if(p.getCantitate()==0.5){
				suma05L+=sumaOras(coloana,p.getId());
				}
				if(p.getCantitate()==20.0){
				suma20L+=sumaOras(coloana,p.getId());
				}
			
			}
		
		sumaTotal=suma5L*5.0+suma075L*0.75+suma1L*1.0+suma05L*0.5+suma20L*20.0;
			
		listaSume.add(suma05L);	
		listaSume.add(suma075L);	
		listaSume.add(suma1L);	
		listaSume.add(suma5L);	
		listaSume.add(suma20L);	
		listaSume.add(sumaTotal);	
		
		
		
		return listaSume;
	}
	
	public String adaugaComanda(int coloana,File selectedFile ){
	
		String str="";
		
		try {
		InputStream ExcelFileToRead = new FileInputStream(selectedFile);
		  
		  
		@SuppressWarnings("resource")
		XSSFWorkbook wb = new  XSSFWorkbook(ExcelFileToRead); 
		XSSFSheet sheet = wb.getSheetAt(0);
			
		@SuppressWarnings("rawtypes")
		Iterator rows = sheet.rowIterator(); 
		Produs p;
			
			int[] ints = new int[100];
			int i=0;
			int ok=0;
			int ok1=0;
			
			//pentru fiecare rand
			while( rows.hasNext() ){
				 XSSFRow row = ( XSSFRow) rows.next(); 

			@SuppressWarnings("rawtypes")
			Iterator cells = row.cellIterator(); 
			p = null;
			
			//pentru fiecare coloana
			while( cells.hasNext() ) {
				
				   XSSFCell cell = ( XSSFCell) cells.next();
				     if( XSSFCell.CELL_TYPE_NUMERIC==cell.getCellType()) { 
				    	 if(ok==1) {ok1++;}
				    	 if(ok1==2) {ok=0;ok1=0;
				    	    if(p!=null) setValoareSuplimentare(p,coloana,cell.getNumericCellValue()); 
				    	 }
				     }
				     else if(XSSFCell.CELL_TYPE_STRING==cell.getCellType()) {
				    	 if(metoda(cell.getStringCellValue())) {
				    		 ok=1;
				    	 int verif=0;
				    	 try{
				    	 verif = Integer.parseInt(cell.getStringCellValue()); 
				    	 }catch(Exception e1){
				    	 }
				    	 
				    	 //verific daca exista produsul in fisier
				    	 p = comand.search(verif);
				    			 
				     	       if(p == null) {
				    	 	    ints[i] = verif;
				    			i++;
				     			}
				    	 }
				     }
				     	else {
				     	}
				     }
			   } 
			
		
			for(int j=0; j<ints.length;j++){
				if(ints[j]>0) {
					str+="Produsul cu numarul " + ints[j] + " lipseste" + "\n";
					
				}
			}
						
	    } catch (Exception e) {
	    }
	 
		   comand.writeProduse();
			return str;
	}
	

	private void setValoareSuplimentare(Produs p, int coloana,double valoare) {
		switch (coloana) {
		case 3 : p.setARAD(p.getARAD()+valoare); break;
		case 4: p.setCLUJ(p.getCLUJ()+valoare); break;
		case 5: p.setBRAS(p.getBRAS()+valoare); break;
		case 6: p.setBUCU(p.getBUCU()+valoare); break;
		case 7: p.setPITE(p.getPITE()+valoare); break;
		case 8: p.setFALTI(p.getFALTI()+valoare); break;
		case 9: p.setBACAU(p.getBACAU()+valoare); break;
		case 10: p.setALBA(p.getALBA()+valoare); break;
		case 11: p.setCONST(p.getCONST()+valoare); break;
		case 12: p.setBIHOR(p.getBIHOR()+valoare); break;
		default: ;
		}
	}
	
	public void curatare(){
		
		for(Produs p: comand.returnList()){
		p.setALBA(0);
		p.setARAD(0);
		p.setCLUJ(0);
		p.setBRAS(0);
		p.setBUCU(0);
		p.setPITE(0);
		p.setFALTI(0);
		p.setBACAU(0);
		p.setCONST(0);
		p.setBIHOR(0);
		}
		comand.writeProduse();
	}
	
	
	
	public void stergeColoana(String input){
		
		int coloana=0;
		
		coloana= alegereOras(input);
		
		for(Produs p:comand.returnList()){
			
				if(coloana==3) p.setARAD(0);
				if(coloana==4) p.setCLUJ(0);
				if(coloana==5) p.setBRAS(0);
				if(coloana==6) p.setBUCU(0);
				if(coloana==7) p.setPITE(0);
				if(coloana==8) p.setFALTI(0);
				if(coloana==9) p.setBACAU(0);
				if(coloana==10) p.setALBA(0);
				if(coloana==11) p.setCONST(0);
				if(coloana==12) p.setBIHOR(0);
		}
			
		
		comand.writeProduse();
	
	}
	
	  public List<Produs> listaProduse(){
			
			return comand.returnList();
	}
	
	private boolean metoda(String stringCellValue) {
		// TODO Auto-generated method stub
		
		if(stringCellValue.equals("0001") ||
				stringCellValue.equals("0002")||
				stringCellValue.equals("0003")||
				stringCellValue.equals("0004")||
				stringCellValue.equals("0005")||
				stringCellValue.equals("0006")||
				stringCellValue.equals("0007")||
				stringCellValue.equals("0008")||
				stringCellValue.equals("0009")||
				stringCellValue.equals("0010")||
				stringCellValue.equals("0011")||
				stringCellValue.equals("0012")||
				stringCellValue.equals("0014")||
				stringCellValue.equals("0013")||
				stringCellValue.equals("0016")||
				stringCellValue.equals("0015")||
				stringCellValue.equals("0017")||
				stringCellValue.equals("0018")||
				stringCellValue.equals("0019")||
				stringCellValue.equals("0020")||
				stringCellValue.equals("0021")||
				stringCellValue.equals("0022")||
				stringCellValue.equals("0023")||
				stringCellValue.equals("0024")||
				stringCellValue.equals("0025")||
				stringCellValue.equals("0026")||
				stringCellValue.equals("0027")|| 
				stringCellValue.equals("0028")||
				stringCellValue.equals("0029")||
				stringCellValue.equals("0030")||
				stringCellValue.equals("0031")||
				stringCellValue.equals("0032")||
				stringCellValue.equals("0033")||
				stringCellValue.equals("0034")||
				stringCellValue.equals("0035")||
				stringCellValue.equals("0036")||
				stringCellValue.equals("0037")||
				stringCellValue.equals("0038")||
				stringCellValue.equals("0039")||
				stringCellValue.equals("0040")||
				stringCellValue.equals("0041")||
				stringCellValue.equals("0042")||
				stringCellValue.equals("0043")||
				stringCellValue.equals("0044")||
				stringCellValue.equals("0045")||
				stringCellValue.equals("0046")||
				stringCellValue.equals("0047")||
				stringCellValue.equals("0048")||
				stringCellValue.equals("0049")||
				stringCellValue.equals("0050")||
				stringCellValue.equals("0051")||
				stringCellValue.equals("0052")||
				stringCellValue.equals("0053")||
				stringCellValue.equals("0054")||
				stringCellValue.equals("0055")||
				stringCellValue.equals("0056")||
				stringCellValue.equals("0057")||
				stringCellValue.equals("0058")||
				stringCellValue.equals("0059")||
				stringCellValue.equals("0060")||
				stringCellValue.equals("0061")||
				stringCellValue.equals("0062")||
				stringCellValue.equals("0063")||
				stringCellValue.equals("0064")||
				stringCellValue.equals("0065")||
				stringCellValue.equals("0066")||
				stringCellValue.equals("0067")||
				stringCellValue.equals("0068")||
				stringCellValue.equals("0069")||
				stringCellValue.equals("0070")||
				stringCellValue.equals("0071")||
				stringCellValue.equals("0072")||
				stringCellValue.equals("0073")||
				stringCellValue.equals("0074")||
				stringCellValue.equals("0075")||
				stringCellValue.equals("0076")||
				stringCellValue.equals("0077")||
				stringCellValue.equals("0078")||
				stringCellValue.equals("0079")||
				stringCellValue.equals("0080")||
				stringCellValue.equals("0081")||
				stringCellValue.equals("0082")||
				stringCellValue.equals("0083")||
				stringCellValue.equals("0084")||
				stringCellValue.equals("0085")||
				stringCellValue.equals("0086")||
				stringCellValue.equals("0087")||
				stringCellValue.equals("0088")||
				stringCellValue.equals("0089")||
				stringCellValue.equals("0090")||
				stringCellValue.equals("0091")||
				stringCellValue.equals("0092")||
				stringCellValue.equals("0093")||
				stringCellValue.equals("0094")||
				stringCellValue.equals("0095")||
				stringCellValue.equals("0096")||
				stringCellValue.equals("0097")||
				stringCellValue.equals("0099")||
				stringCellValue.equals("0098")||
				stringCellValue.equals("00DGP")||
				stringCellValue.equals("2108")||
				stringCellValue.equals("2106")||
				stringCellValue.equals("30861")||
				stringCellValue.equals("30872")) return true;
		else return false;
		
	}
	
	private double sumaOras(int coloana,int numar){
		try{
		switch(coloana){
		case 3:{
			
			Produs p = comand.search(numar);
			if(p.getARAD()>0)return p.getARAD();
			else return 0;	
		}
		case 4:{
			
	
			Produs p = comand.search(numar);
			if(p.getCLUJ()>0)return p.getCLUJ();
			else return 0;
		}
		case 5:{
	
			Produs p = comand.search(numar);
			if(p.getBRAS()>0)return p.getBRAS();
			else return 0;	
		}
		case 6:{
			Produs p = comand.search(numar);
			if(p.getBUCU()>0)return p.getBUCU();
			else return 0;
			
		}
		case 7:{

			Produs p = comand.search(numar);
			if(p.getPITE()>0)return p.getPITE();
			else return 0;
			
		}
		case 8:{

			Produs p = comand.search(numar);
			if(p.getFALTI()>0)return p.getFALTI();
			else return 0;
		}
		case 9:{

			Produs p = comand.search(numar);
			if(p.getBACAU()>0) return p.getBACAU();
			else return 0;
			
		}
		case 10:{

			Produs p = comand.search(numar);
			if(p.getALBA()>0)return p.getALBA();
			else return 0;
			
		}
		case 11:{
		
			Produs p = comand.search(numar);
			if(p.getCONST()>0) return p.getCONST();
			else return 0;
		}
		case 12:{
			Produs p = comand.search(numar);
			if(p.getBIHOR()>0) return p.getBIHOR();
			else return 0;
		}
		default: return 0;
		}
		}
	catch(Exception e) {
		//
	}
		return 0;
	}
	
	
	public int alegereOras(String input){
		if(input.equals("ARAD")) return 3;
		if(input.equals("CLUJ")) return 4;
		if(input.equals("BRASOV")) return 5;
		if(input.equals("BUCURESTI")) return 6;
		if(input.equals("ARGES")) return 7;
		if(input.equals("SUCEAVA")) return 8;
		if(input.equals("BACAU")) return 9;
		if(input.equals("ALBA")) return 10;
		if(input.equals("CONSTANTA")) return 11;
		if(input.equals("BIHOR")) return 12;
		return 0;
	}
}
