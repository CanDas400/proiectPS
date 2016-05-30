package Models;

import java.io.Serializable;

public class Produs implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public double getARAD() {
		return ARAD;
	}

	public void setARAD(double aRAD) {
		ARAD = (int) aRAD;
	}

	public double getCLUJ() {
		return CLUJ;
	}

	public void setCLUJ(double cLUJ) {
		CLUJ = (int) cLUJ;
	}

	public double getBRAS() {
		return BRAS;
	}

	public void setBRAS(double bRAS) {
		BRAS = (int) bRAS;
	}

	public double getBUCU() {
		return BUCU;
	}

	public void setBUCU(double bUCU) {
		BUCU = (int) bUCU;
	}

	public double getPITE() {
		return PITE;
	}

	public void setPITE(double pITE) {
		PITE = (int) pITE;
	}

	public double getFALTI() {
		return FALTI;
	}

	public void setFALTI(double fALTI) {
		FALTI = (int) fALTI;
	}

	public double getBACAU() {
		return BACAU;
	}

	public void setBACAU(double bACAU) {
		BACAU = (int) bACAU;
	}

	public double getALBA() {
		return ALBA;
	}

	public void setALBA(double aLBA) {
		ALBA = (int) aLBA;
	}

	public double getCONST() {
		return CONST;
	}

	public void setCONST(double cONST) {
		CONST = (int) cONST;
	}

	public double getBIHOR() {
		return BIHOR;
	}

	public void setBIHOR(double d) {
		BIHOR = (int) d;
	}
	private String nume;
	private double cantitate;
	private int numar;
	private int ARAD,CLUJ,BRAS,BUCU,PITE,FALTI,BACAU,ALBA,CONST,BIHOR;
	
	public Produs(){
		
	}
	
	public Produs(String produs,Double cantitate, int id){
		this.nume = produs;
		this.numar= id;
		this.cantitate = cantitate;
		
	}
	
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public int getId() {
		return numar;
	}
	public void setId(int id) {
		this.numar = id;
	}
	public double getCantitate() {
		return cantitate;
	}
	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}
	

}
