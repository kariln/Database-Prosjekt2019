package modeller;

import java.sql.Connection;

public class �velse_apparat extends �velse implements ActiveDomainObject{

	private int kg;
	private int antall_sett;
	private Apparat apparat;
	
	public �velse_apparat(int id, String navn, int kg, int sett) {
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
