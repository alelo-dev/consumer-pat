package br.com.alelo.consumer.consumerpat.entity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "address")
public class Address implements Serializable {


	private static final long serialVersionUID = 8844355162730674338L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "street", nullable = false)
	private String street;
	
	@Column(name = "number", nullable = false)
	private String number;
	
	@Column(name = "city", nullable = false)
	private String city;
	
	@Column(name = "country", nullable = false)
	private String country;
	
	@Column(name = "postalCode", nullable = false)
    private String postalCode;
    
 
	public Address() {
	}

	//-----------Getters and Setters-------------//
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getPostalCode() {
		return postalCode;
	}


	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}