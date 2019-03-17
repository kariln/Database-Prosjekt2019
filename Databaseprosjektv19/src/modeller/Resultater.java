package modeller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Resultater implements ActiveDomainObject{
	
	private int form;
	private int prestasjon;
	private Treningsøkt treningsøkt;
	private int id;
	
	public Resultater(Treningsøkt treningsøkt, int form, int prestasjon) {
		this.treningsøkt = treningsøkt;
		this.id = treningsøkt.getØkt_id();
		this.form = form;
		this.prestasjon = prestasjon;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getForm() {
		return this.form;
	}
	
	public int getPrestasjon() {
		return this.prestasjon;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setForm(int form) {
		this.form = form;
	}
	
	public void setPrestasjon(int prestasjon) {
		this.prestasjon = prestasjon;
	}

	@Override
	public void initialize(Connection conn) {
		try {
			String SQL = "select form, prestasjon from Resultater where id=?";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setInt(1, this.id);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				this.form = rs.getInt("form");
				this.prestasjon = rs.getInt("prestasjon");
			}
		}catch (SQLException e) {
				System.out.println("db error during select of Resultater: " + e.getMessage());
		}
	}
	

	@Override
	public void refresh(Connection conn) {
		initialize(conn);
		
	}

	@Override
	public void save(Connection conn) {
		try {
			String SQL = "update save set form=?, prestasjon=? where id=?";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setInt(1, this.prestasjon);
			st.setInt(1, this.form);
			
		}catch (SQLException e) {
			System.out.println("db error during update of Resultater: " + e.getMessage());
		}
		
	}

	@Override
	public void add(Connection conn) {
		try {
			String SQL = "insert into Resultater (form, prestasjon) values (?,?,?)";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setInt(1, this.form);
			st.setInt(1,this.prestasjon);
		}catch (SQLException e) {
			System.out.println("db error during insertion to Resultater: " + e.getMessage());
		}
		
	}
	
	public String toString() {
		return "Treningsøkt:" + this.id + ", Prestasjon:" + this.prestasjon + ", Form:" + this.form;
	}

}
