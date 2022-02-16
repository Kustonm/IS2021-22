package businessLogic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import dataAccess.DataAccess;
import domain.RuralHouse;
import domain.Offer;

public class MyOfferManager implements OfferManager{
	//ArrayList<RuralHouse> ruralHouses;
	
	DataAccess dbManager= new DataAccess(true);
	String ini = "initialize";
	
	public MyOfferManager () {
		//ruralHouses = new ArrayList<RuralHouse>();

		RuralHouse rh1 = new RuralHouse("Donostia","Avenida, 7");

		rh1.addOffer(newDate(2020,2,20),0,2,3);
		rh1.addOffer(newDate(2020,2,1),0,2,3);  
		rh1.addOffer(newDate(2020,2,2),3,3,0);
		rh1.addOffer(newDate(2020,2,3),3,3,0);
		rh1.addOffer(newDate(2020,2,4),3,3,0);
		rh1.addOffer(newDate(2022,2,20),3,3,3);
		rh1.addOffer(newDate(2020,2,6),3,3,0);
		rh1.addOffer(newDate(2020,2,7),3,3,0);
		rh1.addOffer(newDate(2020,2,8),0,0,3);
		rh1.addOffer(newDate(2020,2,9),0,0,3);
		rh1.addOffer(newDate(2020,2,10),0,0,3);
		rh1.addOffer(newDate(2020,2,11),0,0,3);
		rh1.addOffer(newDate(2020,2,12),0,1,3);
		rh1.addOffer(newDate(2020,2,13),1,1,1);
		rh1.addOffer(newDate(2022,2,20),1,1,3);

		RuralHouse rh2 = new RuralHouse("Donostia","San Martin, 13");
		rh2.addOffer(newDate(2020,2,20),1,1,1);

		RuralHouse rh3 = new RuralHouse("Bilbo","Zabalburu, 6");
		rh3.addOffer(newDate(2020,2,1),1,1,1);
		rh3.addOffer(newDate(2020,2,2),0,1,0);
		rh3.addOffer(newDate(2020,2,20),1,0,1);

		dbManager.open(true);
		dbManager.createRuralHouse(rh1);
		dbManager.createRuralHouse(rh2);
		dbManager.createRuralHouse(rh3);
		dbManager.close();		


	}
	public Collection<Offer> getConcreteOffers(String city, Date date) {

		ArrayList<Offer> res = new ArrayList<Offer>();
		dbManager.open(false);
		ArrayList<RuralHouse> dbRuralHouses = (ArrayList<RuralHouse>) dbManager.getRuralHouses();
		//System.out.println("rural houses: \n"+dbRuralHouses); 
		
		for (RuralHouse rh : dbRuralHouses) 
			if ((rh.getCity().equals(city)))
				for (Offer off : rh.getOffers()) 
					if ((off.getDate().compareTo(date))==0) 
						res.add(off);
		System.out.println(res);
		return res;	
	}
	public Offer updateOffer(Offer offer) {
		dbManager.open(false);
		return dbManager.updateOffer(offer);
	}

	private Date newDate(int year,int month,int day) {

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day,0,0,0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}
}