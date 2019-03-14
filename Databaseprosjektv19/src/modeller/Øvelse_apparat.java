package modeller;

import java.sql.Connection;

public class Øvelse_apparat extends Øvelse implements ActiveDomainObject{

	private int kg;
	private int antall_sett;
	private Apparat apparat;
	
	public Øvelse_apparat(int id, String navn, int kg, int sett) {
		super(id, navn);
		this.kg = kg;
		this.antall_sett = sett;
	}
	
	
	
	@Override
	public void initialize(Connection conn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Connection conn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Connection conn) {
		// TODO Auto-generated method stub
		
	}

}
