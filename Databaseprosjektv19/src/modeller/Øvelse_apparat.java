package modeller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class �velse_apparat extends �velse implements ActiveDomainObject{

//	private int kg;
//	private int antall_sett;
//	private Apparat apparat;
	private int �kt_id;
	private int apparat_id;
	private �velse �velse; 
	private Apparat apparat;
	
	public �velse_apparat(�velse �velse, Apparat apparat) {
		this.�velse = �velse;
		this.apparat = apparat;
		�kt_id = �velse.get�velse_id();
		apparat_id = apparat.getApparatId();
	}
	
	
	
	@Override
	public void initialize(Connection conn) {
		// TODO Auto-generated method stub
		try {
			String SQL = "select apparat_id, brukerinstruks from apparat where �kt_id=?";
		    PreparedStatement st = conn.prepareStatement(SQL);
		    st.setInt(1, this.�kt_id);
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
