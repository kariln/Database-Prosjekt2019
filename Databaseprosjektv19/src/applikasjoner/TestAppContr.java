package applikasjoner;

import modeller.Apparat;
import modeller.Dbcon;
import applikasjoner.ApparatController;

public class TestAppContr extends Dbcon{
	
	public static void main(String[] args) {
		ApparatController controller = new ApparatController();
		controller.getDatabase();
		//controller.addApparat("M�lle", "Til l�ping");
		System.out.println(controller.toString());
	}
}
