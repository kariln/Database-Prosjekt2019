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
	
	public Resultater(Treningsøkt treningsøkt) {
		this.treningsøkt = treningsøkt;
		this.id = treningsøkt.getØkt_id();
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Connection conn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Connection conn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(Connection conn) {
		// TODO Auto-generated method stub
		
	}

}
