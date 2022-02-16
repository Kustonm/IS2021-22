package domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAccessType;


@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Offer implements Serializable {

	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer OfferCode;
	
	private Date date;
	private int tripleNumber;
	private int doubleNumber;
	private int singleNumber;
	@XmlIDREF
	private RuralHouse rh;

	public Offer(Date date, int tripleNumber, int doubleNumber, int singleNumber, RuralHouse rh) {
		super();
		this.date = date;
		this.tripleNumber = tripleNumber;
		this.doubleNumber = doubleNumber;
		this.singleNumber = singleNumber;
		this.rh = rh;
	}
	public Integer getOfferCode() {
		return OfferCode;
	}
	public void setOfferCode(Integer OfferCode) {
		this.OfferCode=OfferCode;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getTripleNumber() {
		return tripleNumber;
	}
	public void setTripleNumber(int tripleNumber) {
		this.tripleNumber = tripleNumber;
	}
	public int getDoubleNumber() {
		return doubleNumber;
	}
	public void setDoubleNumber(int doubleNumber) {
		this.doubleNumber = doubleNumber;
	}
	
	public int getSingleNumber() {
		return singleNumber;
	}
	/**
	 * Set the bet number to a question
	 * 
	 * @param singleNumber to be setted
	 */
	public void setSingleNumber(int singleNumber) {
		this.singleNumber = singleNumber;
	}

	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return rh.toString()+"/ "+df.format(date);}
}


