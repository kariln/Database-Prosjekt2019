package modeller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;


public class Logg implements ActiveDomainObject{
	
	private Treningsøkt treningsøkt;
	private Øvelse øvelse;
	private int sett;
	private int rep;
	private int kilo;
	private int økt_id;
	private int øvelse_id;
	
	public Logg(Treningsøkt treningsøkt, Øvelse øvelse,int sett, int, rep, int kilo) {
		this.treningsøkt = treningsøkt;
		this.øvelse = øvelse;
		this.økt_id = treningsøkt.getØkt_id();
		this.øvelse_id = øvelse.getØvelseId();
		this.sett = sett;
		this.rep = rep;
		this.kilo = kilo;
	}
	
	public String toString() {
		return "Økt:" + this.økt_id + "Øvelse:" + this.øvelse_id + "Kilo:" + this.kilo + "Repetisjon:" + this.rep + "Sett:" + this.sett;
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
			System.out.println("db error during insertion to logg: " + e.getMessage());
		}
		
	}
	
	public static List<Logg> listLogger(Connection conn){
		try {
			String SQL = "Select * from Logg";
			PreparedStatement st = conn.prepareStatement(SQL);
			ResultSet rs = st.executeQuery();
			
			List<Logg> logger = new ArrayList<>();
			
			while (rs.next()) {
				int sett = rs.getInt("sett");
				int rep = rs.getInt("rep");
				int kilo = rs.getInt("kilo");
				
				logger.add(new Logg());
				//hvordan få inn de to objektene?
			}
			
		} catch (SQLException e) {
			System.out.println("db error during select from logg: " + e.getMessage());
		}
	}

}
