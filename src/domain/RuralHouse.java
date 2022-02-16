package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


import javax.xml.bind.annotation.XmlAccessorType;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class RuralHouse implements Serializable {

	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer ruralCode;
	//private static final long serialVersionUID = 1L;
	private static int numberOfHouses=1;
	private String city;
	private String address;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Collection<Offer> offers;



	public RuralHouse(String city,String address) {
		super();
		ruralCode=RuralHouse.numberOfHouses++;
		this.city = city;
		this.address=address;
		offers = new ArrayList<Offer>();
	}

	public Integer getRuralCode() {
		return ruralCode;
	}
	public void setRuralCode(Integer houseNumber) {
		this.ruralCode = houseNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public void add(Offer o){
		offers.add(o);
	}

	public Collection<Offer> getOffers() {
		return offers;
	}

	public void setOffers(Collection<Offer> offers) {
		this.offers = offers;
	}
	public String toString() {return ruralCode+"/"+city+"/"+address;}

	public Offer addOffer(Date date, int tripleNumber, int doubleNumber, int singleNumber){
		Offer o=new Offer(date, tripleNumber, doubleNumber, singleNumber, this);
		offers.add(o);
		return o;
	}
}
