package br.com.alelo.consumer.consumerpat.dto;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel (description = "Represents the data needed to add an address")
public class AddressDto {

	@ApiModelProperty(required = true)
	@NotEmpty(message = "The street 'Name' is mandatory.")
	private String street;

	@ApiModelProperty(required = true)
	@NotEmpty(message = "The field 'number' is mandatory.")
	private String number;
	
	@ApiModelProperty(required = true)
	@NotEmpty(message = "The field 'city' is mandatory.")
	private String city;
	
	@ApiModelProperty(required = true)
	@NotEmpty(message = "The field 'country' is mandatory.")
	private String country;
	
	@ApiModelProperty(required = true)
	@NotEmpty(message = "The field 'postalCode' is mandatory.")
    private String postalCode;


	public AddressDto() {
	}
	
	//-----------Getters and Setters-------------//	
	
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