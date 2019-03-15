package modeller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.*;

public class Trenings�kt implements ActiveDomainObject{
	private int �kt_id;
	//usikker p� declaration her, det finnes nok noe for date/time
	private Timestamp time;
	private int varighet;
	
	//konstrukt�r, ingen objekter kan lages uten � f� tildelt id
	public Trenings�kt(int �kt_id) {
		this.�kt_id = �kt_id;
	}
	
	//lage get og set for dato, tid og varighet	
	public int get�kt_id() {
		return this.�kt_id;
	}
	
	public void setVarighet(int tid) {
		this.varighet = tid;
	}
	
	public int getVarighet() {
		return this.varighet;
	}
	
	//implementere init, save og add
	@Override
	public void initialize(Connection conn) {
		try {
			String SQL = "select (dato_tidspunkt,varighet) from (trenings�kt) where �kt_id=?";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setInt(1,this.�kt_id);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				this.time = rs.getTimestamp("dato_tidspunkt");
				this.varighet = rs.getInt("varighet");
			}
			
		} catch (SQLException e) {
			System.out.println("db error during select of trenings�kt" + e.getMessage());	
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

	public void add(Connection conn) {
		
	}
	
}