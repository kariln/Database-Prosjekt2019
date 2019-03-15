package applikasjoner;

import java.util.List;
import java.sql.Connection;
import java.util.ArrayList;
import modeller.Apparat;
import modeller.Dbcon;

public class ApparatController{
	public static void main(String[] args) {
		Dbcon connection = new Dbcon();
		connection.connect();
		Connection connect = connection.getConnection();
	
		List<Apparat> apparat = new ArrayList<>();
		apparat = Apparat.listApparater(connect);	
	}
}
