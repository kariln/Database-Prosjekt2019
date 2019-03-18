package modeller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Treningsøkt implements ActiveDomainObject{
	private int økt_id;
	//usikker på declaration her, det finnes nok noe for date/time
	private Timestamp dato_tidspunkt;
	private int varighet;
	
	//konstruktør, ingen objekter kan lages uten å få tildelt id
	public Treningsøkt(int økt_id) {
		this.økt_id = økt_id;
	}
	
	public Treningsøkt(int økt_id, int varighet) {
		this.økt_id = økt_id;
		this.dato_tidspunkt = new Timestamp(System.currentTimeMillis());
		this.varighet = varighet;
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
				this.dato_tidspunkt = rs.getTimestamp("dato_tidspunkt");
				this.varighet = rs.getInt("varighet");
			}
			
		} catch (SQLException e) {
			System.out.println("db error during select of treningsøkt" + e.getMessage());	
		}
	}
	
	@Override
	public void refresh(Connection conn) {
		initialize(conn);
	}

	@Override
	public void save(Connection conn) {
		try {
			String SQL = "update treningsøkt set dato_tidspunkt=?, varighet=? where økt_id=?";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setTimestamp(1, this.dato_tidspunkt);
			st.setInt(2, this.varighet);
			st.setInt(3, this.økt_id);
			st.execute();
		} catch (SQLException e) {
			System.out.println("db error during update of treningsøkt: " +e.getMessage());
		}		
	}

	public void add(Connection conn) {
		try {
			String SQL = "insert into treningsøkt (økt_id, dato_tidspunkt, varighet) values (?, ?, ?)";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setInt(1,this.økt_id);
			st.setTimestamp(2, this.dato_tidspunkt);
			st.setInt(3, this.varighet);
			st.execute();
		} catch (SQLException e) {
			System.out.println("db error during insertion to treningsøkt" + e.getMessage());
		}
	}
	
	public static List<Treningsøkt> listTreningsøkter(Connection conn){
		try {
			String SQL = "Select * from treningsøkt";
			PreparedStatement st = conn.prepareStatement(SQL);
			ResultSet rs = st.executeQuery();
			
			List<Treningsøkt> treningsøkter = new ArrayList<>();
			
			while (rs.next()) {
				int økt_id = rs.getInt("økt_id");
				Timestamp tid = rs.getTimestamp("dato_tidspunkt");
				int varighet = rs.getInt("varighet");
				
				treningsøkter.add(new Treningsøkt(økt_id, varighet));
			}
			return treningsøkter;
			
		} catch(SQLException e) {
			System.out.println("db error during selection from treningsøkt" + e.getMessage());
		}
		return null;		
	}
	
	public String toString() {
		return "Treningsøkt: " + this.økt_id + ", dato og tid: " + this.dato_tidspunkt + ", varighet: " + this.varighet;
	}
}