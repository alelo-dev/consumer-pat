package br.com.alelo.consumer.consumerpat.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDto implements Serializable {
	
	private static final long serialVersionUID = -5906345166467723761L;
	
	private String street;
    
    private Integer number;
    
    private String city;
    
    private String country;
    
    private String portalCode;

}
