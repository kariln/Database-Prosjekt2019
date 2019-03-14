package modeller;

import java.sql.Connection;

public class Treningsøkt implements ActiveDomainObject{
	private int økt_id;
	//usikker på declaration her, det finnes nok noe for date/time
	private int dato;
	private int time;
	private int varighet;
	
	//konstruktør, ingen objekter kan lages uten å få tildelt id
	public Treningsøkt(int økt_id) {
		this.økt_id = økt_id;
	}
	
	//lage get og set for dato, tid og varighet
	
	public int getØkt_Id() {
		return this.økt_id;
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
