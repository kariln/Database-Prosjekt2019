package modeller;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class �velse_uten_apparat extends �velse implements ActiveDomainObject{
	private int id;
	private String navn;
	
	
	public �velse_uten_apparat(int id, String navn) {
		super(id,navn);
	}
	
	
	@Override
	public void initialize(Connection conn) {
		// TODO Auto-generated method stub
		try {
			String SQL = "select navn, brukerinstruks from apparat where �velse_id=?";
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
