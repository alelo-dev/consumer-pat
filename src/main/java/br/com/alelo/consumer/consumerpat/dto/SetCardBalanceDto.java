package br.com.alelo.consumer.consumerpat.dto;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModelProperty;

public class SetCardBalanceDto {

	@ApiModelProperty(required = true)
	@NotNull(message = "The field 'id' is mandatory.")
	private Long id;
	
	@ApiModelProperty(required = true)
	@NotNull(message = "The field 'value' is mandatory.")
    private Double value;

	
	//-----------Getters and Setters-------------//
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
}