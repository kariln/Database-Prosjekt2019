package modeller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.*;

public class Treningsøkt implements ActiveDomainObject{
	private int økt_id;
	//usikker på declaration her, det finnes nok noe for date/time
	private Timestamp time;
	private int varighet;
	
	//konstruktør, ingen objekter kan lages uten å få tildelt id
	public Treningsøkt(int økt_id) {
		this.økt_id = økt_id;
	}
	
	//lage get og set for dato, tid og varighet	
	public int getØkt_id() {
		return this.økt_id;
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
			String SQL = "select (dato_tidspunkt,varighet) from (treningsøkt) where økt_id=?";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setInt(1,this.økt_id);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				this.time = rs.getTimestamp("dato_tidspunkt");
				this.varighet = rs.getInt("varighet");
			}
			
		} catch (SQLException e) {
			System.out.println("db error during select of treningsøkt" + e.getMessage());	
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