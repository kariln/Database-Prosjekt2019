package modeller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Notat implements ActiveDomainObject{
	private int økt_id;
	private String formål;
	private int opplevelse;
	private String diverse;
	private Treningsøkt treningsøkt;
	private int form;
	private int prestasjon;
	
	
	public Notat(Treningsøkt treningsøkt, String formål, int opplevelse, String diverse, int form, int prestasjon) {
		this.treningsøkt = treningsøkt;
		this.økt_id = treningsøkt.getØkt_id();
		this.formål = formål;
		this.opplevelse = opplevelse;
		this.diverse = diverse;
		this.prestasjon = prestasjon;
		this.form = form;
	}
	
	public int getId() {
		return this.økt_id;
	}
	public String getFormål() {
		return this.formål;
	}
	
	public void setFormål(String formål) {
		this.formål = formål;
	}
	
	public int getOpplevelse() {
		return this.opplevelse;
	}
	public void setOpplevelse(int opplevelse) {
		if (opplevelse>9 || opplevelse<1) {
			throw new IllegalArgumentException("Må være et tall mellom 1-10");
		}else {
			this.opplevelse = opplevelse;
		}
	}
	// dette er definert i databasen, så ma vil ikke få inn tall som ikke er mellom 1-10
	
	public String getDiverse() {
		return this.diverse;
	}
	public void setDiverse(String diverse) {
		this.diverse = diverse;
	}
	
	//henter data i tabellen, oppdaterer objektet
	@Override
	public void initialize(Connection conn) {
		try {
			String SQL = "select formål, opplevelse, diverse from notat where økt_id=?";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setInt(1, this.økt_id);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				this.formål = rs.getString("formål");
				this.opplevelse = rs.getInt("opplevelse");
				this.diverse = rs.getString("diverse");
			}
		} catch (SQLException e) {
			System.out.println("db error during select of notat: " + e.getMessage());
		}
		
	}
	//refresher data fra tabellen
	@Override
	public void refresh(Connection conn) {
		initialize(conn);
	}
	//lagrer nåværende element i tabellen
	@Override
	public void save(Connection conn) {
		try {
			String SQL = "update save set formål=?, opplevelse=?, diverse=? where økt_id=?";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setString(1, this.formål);
			st.setInt(2, this.opplevelse);
			st.setString(3, this.diverse);
		} catch (SQLException e) {
			System.out.println("db error during update of notat: " + e.getMessage());
		}
	}
	//legger til en ny instans av objektet i tabellen
	public void add(Connection conn) {
		try {
			String SQL = "insert into notat (økt_id, formål, opplevelse, diverse, form, prestasjon) values (?,?,?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setInt(1,this.økt_id);
			st.setString(2, this.formål);
			st.setInt(3, this.opplevelse);
			st.setString(4, this.diverse);
			st.setInt(5, this.form);
			st.setInt(6,this.prestasjon);
			st.execute();
		} catch (SQLException e) {
			System.out.println("db error during insertion to notat: " + e.getMessage());
		}
	}
}
