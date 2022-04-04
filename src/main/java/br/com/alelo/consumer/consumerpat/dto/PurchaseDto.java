package br.com.alelo.consumer.consumerpat.dto;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModelProperty;


public class PurchaseDto {

	@ApiModelProperty(required = true)
	@NotNull(message = "The field 'dateBuy' is mandatory.")
	@PastOrPresent(message = "dateBuy must to be a past date.")
	private Date dateBuy;
	
	@ApiModelProperty(required = true)
	@Positive(message = "The field 'value' must to be positive.")
	@NotNull(message = "value is mandatory.")
	private Double value;	
	
	@ApiModelProperty(required = true)
	@NotNull(message = "The field 'cardNumber' is mandatory.")
	private Integer cardNumber;
	
	@ApiModelProperty(required = true)
	@NotNull(message = "The field 'establishmentId' is mandatory.")	
	private Long establishmentId;
	
	@ApiModelProperty(required = true)
	@NotBlank(message = "The field 'productDescription' is mandatory.")		
	String productDescription;

	
	//-----------Getters and Setters-------------//
	
	public Date getDateBuy() {
		return dateBuy;
	}

	public void setDateBuy(Date dateBuy) {
		this.dateBuy = dateBuy;
	}

	
	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	
	public Integer getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Integer cardNumber) {
		this.cardNumber = cardNumber;
	}

	
	public Long getEstablishmentId() {
		return establishmentId;
	}

	public void setEstablishmentId(Long establishmentId) {
		this.establishmentId = establishmentId;
	}

	
	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}


}