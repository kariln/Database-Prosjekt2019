package applikasjoner;

import java.sql.Timestamp;

import java.sql.Connection;

import java.sql.Date;


import modeller.Dbcon;
import modeller.Logg;
import modeller.Øvelse;



public class Main_Kari {
	
	private static Timestamp tid;
	
//	public static Timestamp getTimestamp() {
//		Timestamp tid = new Timestamp(System.currentTimeMillis());
//		return tid;
//	}
	
	public static void main(String[] args) {
		Dbcon connection = new Dbcon();
		connection.connect();
		Connection connect = connection.getConnection();
		
		System.out.println("linje 10");
		tid = Logg.getTimestamp();
		System.out.println(tid);
		
		ØvelseController controller = new ØvelseController();
		controller.addNewØvelse("mølle","løpe",true);
		controller.addNewØvelse("Løping", "Utetrening", false);
		
		Treningsøktcontroller controller2 = new Treningsøktcontroller();
		
		
//		Logg logg1 = new Logg(1,tid,2,3,4);
//		Logg logg2 = new Logg(5,tid,6,7,8);
//		System.out.println("Logg1:" + logg1);
//		System.out.println("Logg2:" + logg2);
//		logg1.initialize(connect);
//		logg1.add(connect);
		controller.addLogg(1, tid, 1, 2, 3, connect);
		controller.addLogg(2, tid, 2, 2, 2, connect);
		//System.out.println("linje 37");
		
		controller.findLoggInterval("2019-03-19 23:59:20", "2019-05-13 23:07:23");
		controller2.findTreningsmengde("2019-03-19 23:59:20", "2019-05-13 23:07:23");
	}

}
