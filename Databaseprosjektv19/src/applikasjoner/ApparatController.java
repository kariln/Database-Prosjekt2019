package applikasjoner;

import java.util.List;
import java.sql.Connection;
import java.util.ArrayList;
import modeller.Apparat;
import modeller.Dbcon;

public class ApparatController{
	List<Apparat> apparat = new ArrayList<>();

	public void getDatabase() {
		Dbcon connection = new Dbcon();
		connection.connect();
		Connection connect = connection.getConnection();
		apparat = Apparat.listApparater(connect);
	}
}

