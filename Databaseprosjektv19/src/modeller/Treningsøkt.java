package modeller;

import java.sql.Connection;

public class Trenings�kt implements ActiveDomainObject{
	private int �kt_id;
	//usikker p� declaration her, det finnes nok noe for date/time
	private int dato;
	private int time;
	private int varighet;
	
	//konstrukt�r, ingen objekter kan lages uten � f� tildelt id
	public Trenings�kt(int �kt_id) {
		this.�kt_id = �kt_id;
	}
	
	//lage get og set for dato, tid og varighet
	
	public int get�kt_Id() {
		return this.�kt_id;
	}
	

	//implementere init, save og add
	@Override
	public void initialize(Connection conn) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void refresh(Connection conn) {
		// TODO Auto-generated method stub
		initialize(conn);
	}


	@Override
	public void save(Connection conn) {
		// TODO Auto-generated method stub
		
	}

	public void add(Connection conn) {
		
	}
	
}
