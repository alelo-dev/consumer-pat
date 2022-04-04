package br.com.alelo.consumer.consumerpat.entity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "contact")
public class Contact implements Serializable {


	private static final long serialVersionUID = 3711909434844335747L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "mobilePhoneNumber", nullable = true)	
	private String mobilePhoneNumber;

	@Column(name = "residencePhoneNumber")	
	private String residencePhoneNumber;
	
	@Column(name = "phoneNumber")	
	private String phoneNumber;
	
	@Column(name = "email", nullable = false)	
	private String email;
    
 
	public Contact() {
	}

	//-----------Getters and Setters-------------//
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}


	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}


	public String getResidencePhoneNumber() {
		return residencePhoneNumber;
	}


	public void setResidencePhoneNumber(String residencePhoneNumber) {
		this.residencePhoneNumber = residencePhoneNumber;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

}