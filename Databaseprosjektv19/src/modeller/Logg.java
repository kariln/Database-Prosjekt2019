package modeller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Logg implements ActiveDomainObject{
	
	private Treningsøkt treningsøkt;
	private Øvelse øvelse;
	private int sett;
	private int rep;
	private int kilo;
	private int øktid;
	private int øvelseid;
	
	public Logg(Treningsøkt treningsøkt, Øvelse øvelse) {
		this.treningsøkt = treningsøkt;
		this.øvelse = øvelse;
		this.øktid = treningsøkt.getØkt_id();
		this.øvelseid = øvelse.getØvelseId();
	}
	
	public String toString() {
		return "Økt:" + this.øktid + "Øvelse:" + this.øvelseid + "Kilo:" + this.kilo + "Repetisjon:" + this.rep + "Sett:" + this.sett;
	}
	
	public int getSett() {
		return this.sett;
	}
	
	public int getRep() {
		return this.rep;
	}
	
	public int getKilo() {
		return this.kilo;
	}
	
	public void setSett(int sett) {
		this.sett = sett;
	}
	
	public void setRep(int rep) {
		this.rep = rep;
	}
	
	public void setKilo(int kilo) {
		this.kilo = kilo;
	}

	@Override
	public void initialize(Connection conn) {
		try {
			String SQL = "select sett, rep, kilo from Logg where (øktid,øvelseid) values (?,?)";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setInt(1, this.sett);
			st.setInt(2, this.rep);
			st.setInt(3, this.kilo);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				this.sett = rs.getInt("sett");
				this.rep = rs.getInt("repetisjon");
				this.kilo = rs.getInt("kilo");
			}
		}catch (SQLException e) {
			System.out.println("db error during select of logg: " + e.getMessage());
		}
		
	}

	@Override
	public void refresh(Connection conn) {
		initialize(conn);
	}

	@Override
	public void save(Connection conn) {
		try {
			String SQL = "update save set sett=?, rep=?, kilo=? where (øktid,øvelseid) values (?,?)";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setInt(1, this.sett);
			st.setInt(2, this.rep);
			st.setInt(3,this.kilo);
		}catch (SQLException e) {
			System.out.println("db error during update of logg: " + e.getMessage());
		}
	}

	@Override
	public void add(Connection conn) {
		try {
			String SQL = "insert into logg (sett,rep,kilo) values (?,?,?)";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setInt(1, this.sett);
			st.setInt(2, this.rep);
			st.setInt(3, this.kilo);
		}catch (SQLException e) {
			System.out.println("db error during insertion to notat: " + e.getMessage());
		}
		
	}
	

}
