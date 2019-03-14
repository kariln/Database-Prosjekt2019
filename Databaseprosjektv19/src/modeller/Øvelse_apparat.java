package modeller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Øvelse_apparat extends Øvelse implements ActiveDomainObject{

//	private int kg;
//	private int antall_sett;
//	private Apparat apparat;
	private int økt_id;
	private int apparat_id;
	private Øvelse øvelse; 
	private Apparat apparat;
	
	public Øvelse_apparat(Øvelse øvelse, Apparat apparat) {
		this.øvelse = øvelse;
		this.apparat = apparat;
		økt_id = øvelse.getØvelse_id();
		apparat_id = apparat.getApparatId();
	}
	
	
	
	@Override
	public void initialize(Connection conn) {
		// TODO Auto-generated method stub
		try {
			String SQL = "select apparat_id, brukerinstruks from apparat where økt_id=?";
		    PreparedStatement st = conn.prepareStatement(SQL);
		    st.setInt(1, this.økt_id);
            ResultSet rs = st.executeQuery();
			
		} catch (Exception e) {
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
