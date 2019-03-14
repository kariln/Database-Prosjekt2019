package modeller;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Øvelse_uten_apparat extends Øvelse implements ActiveDomainObject{
	private int id;
	private String navn;
	
	
	public Øvelse_uten_apparat(int id, String navn) {
		super(id,navn);
	}
	
	
	@Override
	public void initialize(Connection conn) {
		// TODO Auto-generated method stub
		try {
			String SQL = "select navn, brukerinstruks from apparat where øvelse_id=?";
		    PreparedStatement st = conn.prepareStatement(SQL);
		} catch(Exception e) {
            System.out.println("db error during select of apparat: "+e.getMessage());
        }
		
	}

	@Override
	public void refresh(Connection conn) {
		// TODO Auto-generated method stub
		initialize(conn);
	}

	@Override
	public void save(Connection conn) {
		// TODO Auto-generated method stub
		
	}

}
