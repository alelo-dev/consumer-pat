package br.com.alelo.consumer.consumerpat.dto;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Consumidor requisição DTO")
public class ConsumerRequestDTO {
   
    //basic info
    @ApiModelProperty(value = "Name")
    @Length(min = 5, max = 255, message = "Nome")
    private String name;
    
    @ApiModelProperty(value = "Document number")
    @NotBlank(message = "Document number")
    @Min(value = 0, message = "Document number")
    @Max(value = 2147483647, message = "Document number")
    private int documentNumber;
    
    @ApiModelProperty(value = "Birth Date")
    private Date birthDate;

    //contacts
    @ApiModelProperty(value = "Mobile phone number")
    @NotBlank(message = "Mobile phone number")
    @Length(min = 8, max = 9, message = "Mobile phone number")
    private int mobilePhoneNumber;
    
    @ApiModelProperty(value = "Residence phone number")
    @NotBlank(message = "Residence phone number")
    @Length(min = 7, max = 8, message = "Residence phone number")
    private int residencePhoneNumber;
    
    @ApiModelProperty(value = "Phone number")
    @NotBlank(message = "Phone number")
    @Length(min = 7, max = 9, message = "Phone number")
    private int phoneNumber;
    
    @ApiModelProperty(value = "Email")
    @Pattern( regexp = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$", message = "Email")
    private String email;

    //Address
    @ApiModelProperty(value = "Street")
    @Length(min = 0, max = 255, message = "Street")
    private String street;
    
    @ApiModelProperty(value = "Number")
    @NotBlank(message = "Number")
    @Min(value = 0, message = "Number")
    private int number;
    
    @ApiModelProperty(value = "City")
    @Length(min = 0, max = 255, message = "City")
    private String city;
    
    @ApiModelProperty(value = "Country")
    @Length(min = 0, max = 255, message = "Country")
    private String country;
    
    @ApiModelProperty(value = "Portal code")
    @NotBlank(message = "Portal code")
    @Min(value = 0, message = "Portal code")
    @Max(value = 2147483647, message = "Portal code")
    private int portalCode;

    //cards
    @ApiModelProperty(value = "Food card number")
    @NotBlank(message = "Food card number")
    @Min(value = 0, message = "Food card number")
    @Max(value = 2147483647, message = "Food card number")
    private int foodCardNumber;
    
    @ApiModelProperty(value = "Food card balance")
    @NotBlank(message = "Food card balance")
    @Min(value = 0, message = "Food card balance")
    private double foodCardBalance;

    @ApiModelProperty(value = "Fuel card number")
    @NotBlank(message = "Fuel card number")
    @Min(value = 0, message = "Fuel card number")
    @Max(value = 2147483647, message = "Fuel card number")
    private int fuelCardNumber;
    
    @ApiModelProperty(value = "Fuel card balance")
    @NotBlank(message = "Fuel card balance")
    @Min(value = 0, message = "Fuel card balance")
    private double fuelCardBalance;
    
    @ApiModelProperty(value = "Drugstore card number")
    @NotBlank(message = "Drugstore card number")
    @Min(value = 0, message = "Drugstore card number")
    @Max(value = 2147483647, message = "Drugstore card number")
    private int drugstoreNumber;
    
    @ApiModelProperty(value = "Drugstore card balance")
    @NotBlank(message = "Drugstore card balance")
    @Min(value = 0, message = "Drugstore card balance")
    private double drugstoreCardBalance;
    
    public Consumer convertToConsumerEntity() {
    	return new Consumer(name, documentNumber, birthDate, mobilePhoneNumber, residencePhoneNumber, phoneNumber, email, street, number,
    			city, country, portalCode, foodCardNumber, foodCardBalance, fuelCardNumber, fuelCardBalance, drugstoreNumber, 
    			drugstoreCardBalance);
    }
    
    public Consumer convertToConsumerEntity(Integer id) {
    	return new Consumer(id, name, documentNumber, birthDate, mobilePhoneNumber, residencePhoneNumber, phoneNumber, email, street, number,
    			city, country, portalCode, foodCardNumber, foodCardBalance, fuelCardNumber, fuelCardBalance, drugstoreNumber, 
    			drugstoreCardBalance);
    }
    
}
