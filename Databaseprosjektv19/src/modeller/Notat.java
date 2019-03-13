package modeller;

import java.sql.Connection;

public class Notat implements ActiveDomainObject{
	private int id;
//	private int fk_økt;
	private String formål;
	private int opplevelse;
	private String diverse;
	
	public Notat(int pk) {
		this.id = pk;
	}
	
	public int getId() {
		return this.id;
	}
	public String getFormål() {
		return this.formål;
	}
	
	public void setFormål(String formål) {
		this.formål = formål;
	}
	
	public int getOpplevelse() {
		return this.opplevelse;
	}
	public void setOpplevelse(int opplevelse) {
		if (opplevelse>10 || opplevelse<1) {
			throw new IllegalArgumentException("Må være et tall mellom 1-10");
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
	//lagrer nåværende element i tabellen
	@Override
	public void save(Connection conn) {
		// TODO Auto-generated method stub
		
	}
	//legger til en ny instans av objektet i tabellen
	public void add(Connection conn) {
		
	}
}
