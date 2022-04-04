package br.com.alelo.consumer.consumerpat.dto;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

public class ContactDto {

	@ApiModelProperty(required = true)
	@NotEmpty(message = "The field 'mobilePhoneNumber' is mandatory.")
	private String mobilePhoneNumber;

	private String residencePhoneNumber;
	
	private String phoneNumber;
	
	@ApiModelProperty(required = true)
	@NotEmpty(message = "The field 'email' is mandatory.")
	@Email(message = "The field 'email' is mandatory and it mus be in the correct format.")
	private String email;
	
	
	public ContactDto() {
	}

	//-----------Getters and Setters-------------//	
	
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