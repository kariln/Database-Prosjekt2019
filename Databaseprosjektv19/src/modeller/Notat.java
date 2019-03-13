package modeller;

import java.sql.Connection;

public class Notat implements ActiveDomainObject{
	private int id;
//	private int fk_�kt;
	private String form�l;
	private int opplevelse;
	private String diverse;
	
	public Notat(int pk) {
		this.id = pk;
	}
	
	public int getId() {
		return this.id;
	}
	public String getForm�l() {
		return this.form�l;
	}
	
	public void setForm�l(String form�l) {
		this.form�l = form�l;
	}
	
	public int getOpplevelse() {
		return this.opplevelse;
	}
	public void setOpplevelse(int opplevelse) {
		if (opplevelse>10 || opplevelse<1) {
			throw new IllegalArgumentException("M� v�re et tall mellom 1-10");
		}else {
			this.opplevelse = opplevelse;
		}
	}
	
	public String getDiverse() {
		return this.diverse;
	}
	public void setDiverse(String diverse) {
		this.diverse = diverse;
	}
	
	
	//henter data i tabellen, oppdaterer objektet
	@Override
	public void initialize(Connection conn) {
		// TODO Auto-generated method stub
		
	}
	//refresher data fra tabellen
	@Override
	public void refresh(Connection conn) {
		// TODO Auto-generated method stub
		
	}
	//lagrer n�v�rende element i tabellen
	@Override
	public void save(Connection conn) {
		// TODO Auto-generated method stub
		
	}
	//legger til en ny instans av objektet i tabellen
	public void add(Connection conn) {
		
	}
}
