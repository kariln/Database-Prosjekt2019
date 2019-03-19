package modeller;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Trenings�kt implements ActiveDomainObject{
	private int �kt_id;
	//usikker p� declaration her, det finnes nok noe for date/time
	private Timestamp dato_tidspunkt;
	private int varighet;
	
	//konstrukt�r, ingen objekter kan lages uten � f� tildelt id
	public Trenings�kt(int �kt_id) {
		this.�kt_id = �kt_id;
	}
	
	public Trenings�kt(int �kt_id, int varighet) {
		this.�kt_id = �kt_id;
		this.dato_tidspunkt = new Timestamp(System.currentTimeMillis());
		this.varighet = varighet;
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
	
	public Timestamp getTid() {
		return dato_tidspunkt;
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
				this.dato_tidspunkt = rs.getTimestamp("dato_tidspunkt");
				this.varighet = rs.getInt("varighet");
			}
			
		} catch (SQLException e) {
			System.out.println("db error during select of trenings�kt" + e.getMessage());	
		}
	}
	
	@Override
	public void refresh(Connection conn) {
		initialize(conn);
	}

	@Override
	public void save(Connection conn) {
		try {
			String SQL = "update trenings�kt set dato_tidspunkt=?, varighet=? where �kt_id=?";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setTimestamp(1, this.dato_tidspunkt);
			st.setInt(2, this.varighet);
			st.setInt(3, this.�kt_id);
			st.execute();
		} catch (SQLException e) {
			System.out.println("db error during update of trenings�kt: " +e.getMessage());
		}		
	}

	public void add(Connection conn) {
		try {
			String SQL = "insert into trenings�kt (�kt_id, dato_tidspunkt, varighet) values (?, ?, ?)";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setInt(1,this.�kt_id);
			st.setTimestamp(2, this.dato_tidspunkt);
			st.setInt(3, this.varighet);
			st.execute();
		} catch (SQLException e) {
			System.out.println("db error during insertion to trenings�kt" + e.getMessage());
		}
	}
	
	public static List<Trenings�kt> listTrenings�kter(Connection conn){
		try {
			String SQL = "Select * from trenings�kt";
			PreparedStatement st = conn.prepareStatement(SQL);
			ResultSet rs = st.executeQuery();
			
			List<Trenings�kt> trenings�kter = new ArrayList<>();
			
			while (rs.next()) {
				int �kt_id = rs.getInt("�kt_id");
				Timestamp tid = rs.getTimestamp("dato_tidspunkt");
				int varighet = rs.getInt("varighet");
				
				trenings�kter.add(new Trenings�kt(�kt_id, varighet));
			}
			return trenings�kter;
			
		} catch(SQLException e) {
			System.out.println("db error during selection from trenings�kt" + e.getMessage());
		}
		return null;		
	}
	
	public String toString() {
		return "Trenings�kt: " + this.�kt_id + ", dato og tid: " + this.dato_tidspunkt + ", varighet: " + this.varighet;
	}
	
	public void findTreningsmengde(String dato1, String dato2, Connection conn) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dt1 = null;
			Date dt2 = null;
			try {
				dt1 = new java.sql.Date(sdf.parse(dato1).getTime());
				dt2 = new java.sql.Date(sdf.parse(dato2).getTime());				
			} catch (ParseException e) {
				System.out.println(e);
			}
		    //long epoch1 = dt1.getTime();
		    //long epoch2 = dt2.getTime();
		    Timestamp t1 = new Timestamp(dt1.getTime());
		    Timestamp t2 = new Timestamp(dt2.getTime());
			String SQL = " SELECT SUM(varighet) FROM trenings�kt WHERE dato_tidspunkt BETWEEN dato_tidspunkt=? AND dato_tidspunkt=?";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setTimestamp(1, t1);
			st.setTimestamp(2, t2);
		} catch(SQLException e) {
			System.out.println("db error during selection of logg" + e.getMessage());
		}
	}
}