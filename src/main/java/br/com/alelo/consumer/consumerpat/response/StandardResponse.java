package br.com.alelo.consumer.consumerpat.response;
import java.util.ArrayList;
import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel (description = "Represents the default response from endpoints")
public class StandardResponse<ReceivedClass> {

	@ApiModelProperty(value = "Response content")
	private ReceivedClass responseContent;
	
	@ApiModelProperty(value = "Error message list")
	private List<String>  errors;

	
	public StandardResponse(){
	}

	
	//-------------------------Getters and Setters----------------------//	

	public ReceivedClass getResponseContent() {
		
		return responseContent;
	}

	public void setResponseContent( ReceivedClass responseContent ) {
		
		this.responseContent = responseContent;
	}
	
	

	public List<String> getErrors() {
		
		if (this.errors == null) {
			
			//Nao retorna "null", e sim um objeto vazio.
			this.errors = new ArrayList<String>();
		}
		
		return errors;
	}

	public void setErrors( List<String> errors ) {
		
		this.errors = errors;
	}

}