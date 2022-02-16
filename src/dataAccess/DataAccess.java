package dataAccess;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Offer;
import domain.RuralHouse;

public class DataAccess {
	
	public static EntityManager db;
	public static EntityManagerFactory emf;
	
	ConfigXML c  = ConfigXML.getInstance();
	
	
	public DataAccess(boolean initializateMode) {
		 System.out.println("Creando intancia de la Base de Datos");
		 open(initializateMode);
	}
	
	public void cleanDataBase() {
		db.getTransaction().begin();
		db.clear();
		db.getTransaction().commit();
	}
	public DataAccess() {
		this(false);
	}
	
	public void initializeDB() {
		db.getTransaction().begin();
	}
	
	public void open(boolean initializateMode) {
		String fileName  = c.getDbFilename();
		
		if(initializateMode) {
			fileName = fileName+";drop";
			System.out.println("Eliminando base de datos");
		}
		
		if(c.isDatabaseLocal()){
			emf  = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			db  = emf.createEntityManager();
		}else{
			//properties.put("javax.persistence.jdbc.user", c.getUser());
			//properties.put("javax.persistence.jdbc.password", c.getPassword());
			
			emf  = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			db  = emf.createEntityManager();			
		}
	}
	
	private <T> void save(T object) 
	{
		db.getTransaction().begin();
		try {
			db.persist(object);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		db.getTransaction().commit();
	}
	
	private <T> List<T> get(GenericClass type){
		db.getTransaction().begin();
		List<GenericClass> res = new ArrayList<GenericClass>();
		TypedQuery<T> query = db.createQuery("SELECT * FROM ?1 ",type.getMyType());
		query.setParameter(1, type.getClass());
		return query.getResultList();
		
	}
	
	public List<Offer> getOffers()
	{
		db.getTransaction().begin();
		TypedQuery<Offer> query = db.createQuery("SELECT of FROM Offer of ",Offer.class);
		return query.getResultList();
	}
	
	public List<RuralHouse> getRuralHouses()
	{
		db.getTransaction().begin();
		TypedQuery<RuralHouse> query = db.createQuery("SELECT rh FROM RuralHouse rh ",RuralHouse.class);
		return query.getResultList();
	}
	
	public void createOffer(Offer offer) {
		this.save(offer);
	}
	
	public void createRuralHouse2(RuralHouse ruralHouse) {
		db.getTransaction().begin();
		try {
			db.persist(ruralHouse);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		db.getTransaction().commit();
	}
	
	public void createRuralHouse(RuralHouse ruralHouse) {
		this.save(ruralHouse);
	}
	
	public Offer updateOffer(Offer offer) 
	{
		db.getTransaction().begin();
		System.out.println(offer.getOfferCode());
		TypedQuery<Integer> query = db.createQuery("SELECT OfferCode FROM Offer WHERE OfferCode=?1 ", Integer.class);
		query.setParameter(1, offer.getOfferCode());
		System.out.println(query.getResultList());
		try {
			
			//Offer sol=query.getSingleResult();
			//db.getTransaction().commit();
			//return sol;
			return null;
		}catch(Exception e){
			System.out.println(e.getMessage()+e.getStackTrace());
			return null;
		}
	}
	
	
	
	public void close() {
		db.close();
		System.out.println("Cerrando Base de Datos");
	}
	
}
