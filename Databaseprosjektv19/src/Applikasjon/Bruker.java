package Applikasjon;

import java.sql.*;

public class Bruker implements ActiveDomainObject{
	private int bruker_id;
	private String navn;
	
	public Bruker(int bruker_id) {
		this.bruker_id = bruker_id;
	}
	
	public int getBruker_id() {
		return this.bruker_id;
	}
	
	public String getNavn() {
		return this.navn;
	}
	
	@Override
	public void initialize(Connection conn) {
		//TODO Auto-generated method stub
        try {
		    String SQL = "select navn from Bruker where brukerid=?";
		    PreparedStatement st = conn.prepareStatement(SQL);
		    st.setInt(1, bruker_id);
	            ResultSet rs = st.executeQuery();
	            while (rs.next()) {
	                navn =  rs.getString("navn");
	            }
 
        } catch (Exception e) {
            System.out.println("db error during select of bruker= "+e);
            return;
        }	
	}

	@Override
	public void refresh(Connection conn) {
		initialize(conn);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Connection conn) {
		// TODO Auto-generated method stub
		
	}
	
	//PreparedStatement
	

}
