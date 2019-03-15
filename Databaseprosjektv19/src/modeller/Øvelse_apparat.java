package modeller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class �velse_apparat implements ActiveDomainObject{

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
	
	public int get�ktId() {
		return this.�kt_id;
	}
	
	public int getApparatId() {
		return this.apparat_id;
	}
	
	public void set�ktId(int �kt_id) {
		this.�kt_id = �kt_id;
	}
	
	public void setApparatId(int apparat_id) {
		this.apparat_id = apparat_id;
	}
	
	@Override
	public void initialize(Connection conn) {
		// TODO Auto-generated method stub
		try {
			String SQL = "select apparat_id, brukerinstruks from apparat where �kt_id=?";
		    PreparedStatement st = conn.prepareStatement(SQL);
		    st.setInt(1, this.�kt_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
            	this.apparat_id = rs.getInt("apparat_id");
            	this.�kt_id = rs.getInt("�kt_id");
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
			st.setInt(1,�kt_id);
			st.setInt(1, apparat_id);
			
		}catch (SQLException e) {
			System.out.println("db error during update of apparat_�velse" + e.getMessage());
		}
		
	}

	@Override
	public void add(Connection conn) {
		// TODO Auto-generated method stub
		try {
			String SQL = "insert into �velse_apparat (apparat_id, �kt_id) values (?,?)";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setInt(1,this.apparat_id);
			st.setInt(2, this.�kt_id);
			st.execute();
			
		}catch (SQLException e) {
			System.out.println("db error during insertion to apparat: " + e.getMessage());
		}
	}

}
