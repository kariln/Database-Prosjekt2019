package applikasjoner;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main {
	//Scanner scanner = new Scanner(System.in);
	
	//switch();
	
	//test av trenings�ktcontroller og m�l 1
	
	public static void main(String[] args) {
		//String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		Trenings�ktcontroller controller = new Trenings�ktcontroller();
		controller.getDatabase();
//		controller.addTrenings�kt(new Timestamp(System.currentTimeMillis()), 45);
//		controller.addNotat(5, "kondisjon", 5, "spinningtime", 4, 5);
//		controller.addTrenings�kt(new Timestamp(System.currentTimeMillis()), 30);
//		controller.addNotat(6, "noe", 10, "kult", 3, 1);
		controller.getBestemte�kter(6);
		
	}
	
}