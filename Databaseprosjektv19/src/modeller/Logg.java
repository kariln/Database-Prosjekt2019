package modeller;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

//må legge til timestamp

public class Logg implements ActiveDomainObject{
	private Øvelse øvelse;
	private int sett;
	private int repetisjoner;
	private int kg;
	//private int økt_id;
	private int øvelse_id;
	private Timestamp dato_tidspunkt;

	public Logg(int øvelse_id,Timestamp logg_tidspunkt, int sett, int rep, int kilo) {
		this.øvelse_id = øvelse_id;
		this.sett = sett;
		this.repetisjoner = rep; 
		this.kg = kilo;
		this.dato_tidspunkt = logg_tidspunkt;
	}
	
	/*
	public Logg(Øvelse øvelse, Treningsøkt treningsøkt, int sett, int rep, int kilo) {
		this.øvelse_id = øvelse.getØvelseId();
		this.sett = sett;
		this.repetisjoner = rep; 
		this.kg = kilo;
		this.dato_tidspunkt = treningsøkt.getTid();
	}*/
	
	public String toString() {
		return "Øvelse:" + this.øvelse_id + "Kilo:" + this.kg + "Repetisjon:" + this.repetisjoner + "Sett:" + this.sett;
	}
	
	public Timestamp getTime() {
		return dato_tidspunkt;
	}
	
	public int getSett() {
		return this.sett;
	}
	
	public int getRep() {
		return this.repetisjoner;
	}
	
	public int getKilo() {
		return this.kg;
	}
	
	public void setTime(Timestamp logg_tidspunkt) {
		this.dato_tidspunkt = logg_tidspunkt;
	}
	
	public void setSett(int sett) {
		this.sett = sett;
	}
	
	public void setRep(int rep) {
		this.repetisjoner = rep;
	}
	
	public void setKilo(int kilo) {
		this.kg = kilo;
	}

	@Override
	public void initialize(Connection connection) {
		try {
			String SQL = "SELECT øvelse_id, dato_tidspunkt, sett, repetisjoner, kg FROM logg WHERE øvelse_id=?";
			PreparedStatement st = connection.prepareStatement(SQL);
			st.setInt(1, this.øvelse_id);
//			st.setTimestamp(2, this.dato_tidspunkt);
//			st.setInt(3, this.sett);
//			st.setInt(4, this.repetisjoner);
//			st.setInt(5, this.kg);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				this.øvelse_id = rs.getInt("øvelse_id");
				this.dato_tidspunkt = rs.getTimestamp("dato_tidspunkt");
				this.sett = rs.getInt("sett");
				this.repetisjoner = rs.getInt("repetisjoner");
				this.kg = rs.getInt("kg");
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
			System.out.println("hei");
			String SQL = "update logg set dato_tidspunkt=?, sett=?, repetisjoner=?, kg=? WHERE øvelse_id=?";
			PreparedStatement st = connection.prepareStatement(SQL);
			st.setInt(1, this.øvelse_id);
			st.setTimestamp(2, this.dato_tidspunkt);
			System.out.println("hade");
			st.setInt(3, this.sett);
			st.setInt(4, this.repetisjoner);
			st.setInt(5,this.kg);
			st.execute();
		}catch (SQLException e) {
			System.out.println("db error during update of logg: " + e.getMessage());
		}
	}

	@Override
	public void add(Connection connection) {
		try {
			String SQL = "insert into logg (øvelse_id, dato_tidspunkt,sett,repetisjoner,kg) values (?,?,?,?,?)";
			PreparedStatement st = connection.prepareStatement(SQL);
			st.setInt(1, this.øvelse_id);
			st.setTimestamp(2, this.dato_tidspunkt);
			st.setInt(3, this.sett);
			st.setInt(4, this.repetisjoner);
			st.setInt(5,this.kg);
			st.execute();
		}catch (SQLException e) {
			System.out.println("db error during insertion to logg: " + e.getMessage());
		}
		
	}
	

 //overflødig pga fremmednøkkel tror jeg

//	//denne og liste-metoden må jobbes med
//	public void knyttloggtiløvelse(Timestamp logg_tidspunkt, Connection connection) {
//		try {
//			String SQL = "insert into øvelse_logg values (?,?)";
//			PreparedStatement st = connection.prepareStatement(SQL);
//			st.setTimestamp(1,logg_tidspunkt);
//			st.setInt(2, øvelse_id);
//			st.execute();
//		}catch (SQLException e) {
//			System.out.println("db error during insert to øvelse_logg.");
//		}
//		
//	}
//	
//	//hvordan bruker jeg knyttloggogøvelse til listlogg
//	public static List<Logg> listLogger(Connection connection){
//		//har endret metoden fra static til non-static. går det bra?
//		try {
//			String SQL = "Select * from Logg";
//			PreparedStatement st = connection.prepareStatement(SQL);
//			ResultSet rs = st.executeQuery();
//			List<Logg> logger = new ArrayList<>();
//			
//			while (rs.next()) {
//				int sett = rs.getInt("sett");
//				int rep = rs.getInt("rep");
//				int kilo = rs.getInt("kilo");
//				Timestamp logg_tidspunkt = rs.getTimestamp("logg_tidspunkt");
//				int øvelse_id = 
//				
//				Logg logg = new Logg(øvelse_id, logg_tidspunkt,sett, rep, kilo);
//				logger.add(logg);
//				
//				
//			}
//			return logger;
//		} catch (SQLException e) {
//			System.out.println("db error during select from logg: " + e.getMessage());
//		}
//	return null;
//	}
	
	public static Timestamp getTimestamp() {
		Timestamp tid = new Timestamp(System.currentTimeMillis());
		return tid;
	}
	

}
