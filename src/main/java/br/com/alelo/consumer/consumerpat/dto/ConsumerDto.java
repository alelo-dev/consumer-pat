package br.com.alelo.consumer.consumerpat.dto;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.*;

import io.swagger.annotations.ApiModelProperty;

public class ConsumerDto {

	@ApiModelProperty(required = true)
	@NotEmpty(message = "The field 'Name' is mandatory.")
	private String name;

	@ApiModelProperty(required = true)
	@Positive(message = "The field 'documentNumber' must to be positive.")
	@NotNull(message = "documentNumber is mandatory.")
	private Long documentNumber;
	
	@ApiModelProperty(required = true)
	@NotNull(message = "The field 'birthDate' is mandatory.")
	@Past(message = "birthDate must to be a past date.")
	private Date birthDate;
	
	@ApiModelProperty(required = true)
	@Valid //To Validate the fields inside the object.
	@NotNull(message = "The field 'contactDto' is mandatory.")
	private ContactDto contactDto;	
	
	@ApiModelProperty(required = true)
	@Valid //To Validate the fields inside the object.
	@NotNull(message = "The field 'addressDto' is mandatory.")
	private AddressDto addressDto;
	
	@ApiModelProperty(required = true)
	@Valid //To Validate the fields inside the object.
	@NotEmpty(message = "The field 'cards' is mandatory and it cannot be empty.")
	private List<CardDto> cardsDto;
	
	
	public ConsumerDto() {
	}

	//-------------------------Getters and Setters----------------------//
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Long getDocumentNumber() {
		return documentNumber;
	}


	public void setDocumentNumber(Long documentNumber) {
		this.documentNumber = documentNumber;
	}


	public Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public ContactDto getContactDto() {
		return contactDto;
	}


	public void setContactDto(ContactDto contactDto) {
		this.contactDto = contactDto;
	}


	public AddressDto getAddressDto() {
		return addressDto;
	}


	public void setAddressDto(AddressDto addressDto) {
		this.addressDto = addressDto;
	}


	public List<CardDto> getCardsDto() {
		return cardsDto;
	}


	public void setCardsDto(List<CardDto> cardsDto) {
		this.cardsDto = cardsDto;
	}

}