package modeller;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
	
	public Timestamp getTid() {
		return dato_tidspunkt;
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
			String SQL = " SELECT SUM(varighet) FROM treningsøkt WHERE dato_tidspunkt BETWEEN dato_tidspunkt=? AND dato_tidspunkt=?";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setTimestamp(1, t1);
			st.setTimestamp(2, t2);
		} catch(SQLException e) {
			System.out.println("db error during selection of logg" + e.getMessage());
		}
	}
}