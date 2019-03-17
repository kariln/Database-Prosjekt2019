package applikasjoner;
import modeller.Treningsøkt;
import java.util.List;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList; 
import modeller.Dbcon;

public class Treningsøktcontroller {
	private List<Treningsøkt> treningsøkter = new ArrayList<>();
	
	Dbcon connection = new Dbcon();
	
	//init
	public void getDatabase() {
		connection.connect();
		Connection connect = connection.getConnection();
		treningsøkter = Treningsøkt.listTreningsøkter(connect);
	}
	//legge til treningsøkt med tilhørende data
	public void addTreningsøkt(Timestamp tid, int varighet) {
		connection.connect();
		Connection connect = connection.getConnection();
		int øvelse_id = treningsøkter.size()+1;
		Treningsøkt ny = new Treningsøkt(øvelse_id, tid, varighet);
		ny.add(connect);
		treningsøkter.add(ny);
	}
	
	//tilhørende data til en treningsøkt
	//uferdig
	public void addNotat() {
		connection.connect();
		Connection connect = connection.getConnection();
		
	}
	
	//getter for treningsøkt, henter på øvelsesid
	public Treningsøkt getTreningsøkt(int øvelse_id) {
		return treningsøkter.get(øvelse_id-1);
	}
	
	//oppdaterer objekter ihht databasen
	public void refresh() {
		connection.connect();
		Connection connect = connection.getConnection();
		for (Treningsøkt økt: treningsøkter) {
			økt.refresh(connect);
		}
	}
	// få opp n sist gjennomførte treningsøkter med notater, der n spesifiseres av bruker


}
