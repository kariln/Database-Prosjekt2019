package modeller;

import java.sql.Connection;

public class Øvelse_uten_apparat extends Øvelse implements ActiveDomainObject{

	
	public Øvelse_uten_apparat(int id, String navn) {
		super(id,navn);
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
