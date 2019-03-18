package modeller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Notat implements ActiveDomainObject{
	private int �kt_id;
	private String form�l;
	private int opplevelse;
	private String diverse;
	private Trenings�kt trenings�kt;
	private int form;
	private int prestasjon;
	
	
	public Notat(Trenings�kt trenings�kt, String form�l, int opplevelse, String diverse, int form, int prestasjon) {
		this.trenings�kt = trenings�kt;
		this.�kt_id = trenings�kt.get�kt_id();
		this.form�l = form�l;
		this.opplevelse = opplevelse;
		this.diverse = diverse;
		this.prestasjon = prestasjon;
		this.form = form;
	}
	
	public int getId() {
		return this.�kt_id;
	}
	public String getForm�l() {
		return this.form�l;
	}
	
	public void setForm�l(String form�l) {
		this.form�l = form�l;
	}
	
	public int getOpplevelse() {
		return this.opplevelse;
	}
	public void setOpplevelse(int opplevelse) {
		if (opplevelse>9 || opplevelse<1) {
			throw new IllegalArgumentException("M� v�re et tall mellom 1-10");
		}else {
			this.opplevelse = opplevelse;
		}
	}
	// dette er definert i databasen, s� ma vil ikke f� inn tall som ikke er mellom 1-10
	
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
			String SQL = "select form�l, opplevelse, diverse from notat where �kt_id=?";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setInt(1, this.�kt_id);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				this.form�l = rs.getString("form�l");
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
	//lagrer n�v�rende element i tabellen
	@Override
	public void save(Connection conn) {
		try {
			String SQL = "update save set form�l=?, opplevelse=?, diverse=? where �kt_id=?";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setString(1, this.form�l);
			st.setInt(2, this.opplevelse);
			st.setString(3, this.diverse);
		} catch (SQLException e) {
			System.out.println("db error during update of notat: " + e.getMessage());
		}
	}
	//legger til en ny instans av objektet i tabellen
	public void add(Connection conn) {
		try {
			String SQL = "insert into notat (�kt_id, form�l, opplevelse, diverse, form, prestasjon) values (?,?,?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setInt(1,this.�kt_id);
			st.setString(2, this.form�l);
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
