package br.com.alelo.consumer.consumerpat.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactDto implements Serializable {

	private static final long serialVersionUID = -3888274517440368912L;
	    
	private String mobilePhoneNumber;
    
    private String residencePhoneNumber;
    
    private String phoneNumber;
    
    private String email;

}
