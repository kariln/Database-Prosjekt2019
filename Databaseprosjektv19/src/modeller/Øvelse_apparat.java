package modeller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Øvelse_apparat implements ActiveDomainObject{

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
	
	public int getØktId() {
		return this.økt_id;
	}
	
	public int getApparatId() {
		return this.apparat_id;
	}
	
	public void setØktId(int økt_id) {
		this.økt_id = økt_id;
	}
	
	public void setApparatId(int apparat_id) {
		this.apparat_id = apparat_id;
	}
	
	@Override
	public void initialize(Connection conn) {
		// TODO Auto-generated method stub
		try {
			String SQL = "select apparat_id, brukerinstruks from apparat where økt_id=?";
		    PreparedStatement st = conn.prepareStatement(SQL);
		    st.setInt(1, this.økt_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
            	this.apparat_id = rs.getInt("apparat_id");
            	this.økt_id = rs.getInt("økt_id");
            }
			
		} catch (SQLException e) {
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
		try {
			String SQL = "update save set apparat_id=? where apparat_id=?";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setInt(1,økt_id);
			st.setInt(1, apparat_id);
			
		}catch (SQLException e) {
			System.out.println("db error during update of apparat_øvelse" + e.getMessage());
		}
		
	}

	@Override
	public void add(Connection conn) {
		// TODO Auto-generated method stub
		try {
			String SQL = "insert into Øvelse_apparat (apparat_id, økt_id) values (?,?)";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setInt(1,this.apparat_id);
			st.setInt(2, this.økt_id);
			st.execute();
			
		}catch (SQLException e) {
			System.out.println("db error during insertion to apparat: " + e.getMessage());
		}
	}

}
