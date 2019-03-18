package modeller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

//må legge til timestamp

public class Logg implements ActiveDomainObject{
	private Øvelse øvelse;
	private int sett;
	private int rep;
	private int kilo;
	//private int økt_id;
	private int øvelse_id;
	private Timestamp logg_tidspunkt;

	public Logg(int øvelse_id,Timestamp logg_tidspunkt, int sett, int rep, int kilo) {
		this.øvelse_id = øvelse_id;
		this.sett = sett;
		this.rep = rep;
		this.kilo = kilo;
		this.logg_tidspunkt = logg_tidspunkt;
	}
	
	public String toString() {
		return "Øvelse:" + this.øvelse_id + "Kilo:" + this.kilo + "Repetisjon:" + this.rep + "Sett:" + this.sett;
	}
	
	public Timestamp getTime() {
		return logg_tidspunkt;
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
	
	public void setTime(Timestamp logg_tidspunkt) {
		this.logg_tidspunkt = logg_tidspunkt;
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
	public void initialize(Connection connection) {
		try {
			String SQL = "select sett, rep, kilo from Logg where (øktid,øvelseid) values (?,?)";
			PreparedStatement st = connection.prepareStatement(SQL);
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
	public void refresh(Connection connection) {
		initialize(connection);
	}

	@Override
	public void save(Connection connection) {
		try {
			String SQL = "update save set sett=?, rep=?, kilo=? where (øktid,øvelseid) values (?,?)";
			PreparedStatement st = connection.prepareStatement(SQL);
			st.setInt(1, this.sett);
			st.setInt(2, this.rep);
			st.setInt(3,this.kilo);
		}catch (SQLException e) {
			System.out.println("db error during update of logg: " + e.getMessage());
		}
	}

	@Override
	public void add(Connection connection) {
		try {
			String SQL = "insert into logg (sett,rep,kilo) values (?,?,?)";
			PreparedStatement st = connection.prepareStatement(SQL);
			st.setInt(1, this.sett);
			st.setInt(2, this.rep);
			st.setInt(3, this.kilo);
		}catch (SQLException e) {
			System.out.println("db error during insertion to logg: " + e.getMessage());
		}
		
	}
	

 //overflødig pga fremmednøkkel tror jeg

	//denne og liste-metoden må jobbes med
	public void knyttloggtiløvelse(Timestamp logg_tidspunkt, Connection connection) {
		try {
			String SQL = "insert into øvelse_logg values (?,?)";
			PreparedStatement st = connection.prepareStatement(SQL);
			st.setTimestamp(1,logg_tidspunkt);
			st.setInt(2, øvelse_id);
			st.execute();
		}catch (SQLException e) {
			System.out.println("db error during insert to øvelse_logg.");
		}
		
	}
	
	public static List<Logg> listLogger(int øvelse_id, Connection connection){
		//har endret metoden fra static til non-static. går det bra?
		try {
			String SQL = "Select * from Logg";
			PreparedStatement st = connection.prepareStatement(SQL);
			ResultSet rs = st.executeQuery();
			List<Logg> logger = new ArrayList<>();
			
			while (rs.next()) {
				int sett = rs.getInt("sett");
				int rep = rs.getInt("rep");
				int kilo = rs.getInt("kilo");
				Timestamp logg_tidspunkt = rs.getTimestamp("logg_tidspunkt");
				
				Logg logg = new Logg(øvelse_id, logg_tidspunkt,sett, rep, kilo);
				logger.add(logg);
				
				
			}
			return logger;
		} catch (SQLException e) {
			System.out.println("db error during select from logg: " + e.getMessage());
		}
	return null;
	}
	
	
	

}
