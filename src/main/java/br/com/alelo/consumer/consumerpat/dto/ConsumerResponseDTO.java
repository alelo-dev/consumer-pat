package br.com.alelo.consumer.consumerpat.dto;

import java.util.Date;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Consumer response DTO")
public class ConsumerResponseDTO {

	//basic info
	@ApiModelProperty(value = "Id")
    private Integer id;
	
    @ApiModelProperty(value = "Name")
    private String name;
    
    @ApiModelProperty(value = "Document number")
    private int documentNumber;
    
    @ApiModelProperty(value = "Birth Date")
    private Date birthDate;

    //contacts
    @ApiModelProperty(value = "Mobile phone number")
    private int mobilePhoneNumber;
    
    @ApiModelProperty(value = "Residence phone number")
    private int residencePhoneNumber;
    
    @ApiModelProperty(value = "Phone number")
    private int phoneNumber;
    
    @ApiModelProperty(value = "Email")
    private String email;

    //Address
    @ApiModelProperty(value = "Street")
    private String street;
    
    @ApiModelProperty(value = "Number")
    private int number;
    
    @ApiModelProperty(value = "City")
    private String city;
    
    @ApiModelProperty(value = "Country")
    private String country;
    
    @ApiModelProperty(value = "Portal code")
    private int portalCode;

    //cards
    @ApiModelProperty(value = "Food card number")
    private int foodCardNumber;
    
    @ApiModelProperty(value = "Food card balance")
    private double foodCardBalance;

    @ApiModelProperty(value = "Fuel card number")
    private int fuelCardNumber;
    
    @ApiModelProperty(value = "Fuel card balance")
    private double fuelCardBalance;
    
    @ApiModelProperty(value = "Drugstore card number")
    private int drugstoreNumber;
    
    @ApiModelProperty(value = "Drugstore card balance")
    private double drugstoreCardBalance;

	public ConsumerResponseDTO(Integer id, String name, int documentNumber, Date birthDate, int mobilePhoneNumber,
			int residencePhoneNumber, int phoneNumber, String email, String street, int number, String city,
			String country, int portalCode, int foodCardNumber, double foodCardBalance, int fuelCardNumber,
			double fuelCardBalance, int drugstoreNumber, double drugstoreCardBalance) {
		super();
		this.id = id;
		this.name = name;
		this.documentNumber = documentNumber;
		this.birthDate = birthDate;
		this.mobilePhoneNumber = mobilePhoneNumber;
		this.residencePhoneNumber = residencePhoneNumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.street = street;
		this.number = number;
		this.city = city;
		this.country = country;
		this.portalCode = portalCode;
		this.foodCardNumber = foodCardNumber;
		this.foodCardBalance = foodCardBalance;
		this.fuelCardNumber = fuelCardNumber;
		this.fuelCardBalance = fuelCardBalance;
		this.drugstoreNumber = drugstoreNumber;
		this.drugstoreCardBalance = drugstoreCardBalance;
	}
    
	public static ConsumerResponseDTO convertToConsumerResponseDTO(Consumer consumer) {
        return new ConsumerResponseDTO(consumer.getId(), consumer.getName(), consumer.getDocumentNumber(), consumer.getBirthDate(), consumer.getMobilePhoneNumber(),
        		consumer.getResidencePhoneNumber(), consumer.getPhoneNumber(), consumer.getEmail(), consumer.getStreet(), consumer.getNumber(),
        		consumer.getCity(), consumer.getCountry(), consumer.getPortalCode(), consumer.getFoodCardNumber(), consumer.getFoodCardBalance(),
        		consumer.getFuelCardNumber(), consumer.getFuelCardBalance(), consumer.getDrugstoreNumber(), consumer.getDrugstoreCardBalance());
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(int documentNumber) {
		this.documentNumber = documentNumber;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	public void setMobilePhoneNumber(int mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	public int getResidencePhoneNumber() {
		return residencePhoneNumber;
	}

	public void setResidencePhoneNumber(int residencePhoneNumber) {
		this.residencePhoneNumber = residencePhoneNumber;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
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

	public int getPortalCode() {
		return portalCode;
	}

	public void setPortalCode(int portalCode) {
		this.portalCode = portalCode;
	}

	public int getFoodCardNumber() {
		return foodCardNumber;
	}

	public void setFoodCardNumber(int foodCardNumber) {
		this.foodCardNumber = foodCardNumber;
	}

	public double getFoodCardBalance() {
		return foodCardBalance;
	}

	public void setFoodCardBalance(double foodCardBalance) {
		this.foodCardBalance = foodCardBalance;
	}

	public int getFuelCardNumber() {
		return fuelCardNumber;
	}

	public void setFuelCardNumber(int fuelCardNumber) {
		this.fuelCardNumber = fuelCardNumber;
	}

	public double getFuelCardBalance() {
		return fuelCardBalance;
	}

	public void setFuelCardBalance(double fuelCardBalance) {
		this.fuelCardBalance = fuelCardBalance;
	}

	public int getDrugstoreNumber() {
		return drugstoreNumber;
	}

	public void setDrugstoreNumber(int drugstoreNumber) {
		this.drugstoreNumber = drugstoreNumber;
	}

	public double getDrugstoreCardBalance() {
		return drugstoreCardBalance;
	}

	public void setDrugstoreCardBalance(double drugstoreCardBalance) {
		this.drugstoreCardBalance = drugstoreCardBalance;
	}
}
