package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.contants.RegexDefault;
import br.com.alelo.consumer.consumerpat.exception.ValidationException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ConsumerRequest {

    private Integer id;
    private String name;
    @JsonProperty(required = true)
    private String documentNumber;
    private Date birthDate;

    //contacts
    private Integer mobilePhoneNumber;
    private Integer residencePhoneNumber;
    private Integer phoneNumber;
    private String email;

    //Address
    private String street;
    private Integer number;
    private String city;
    private String country;
    private Integer postalCode;

    @JsonCreator
    public ConsumerRequest(
            @JsonProperty(required = true) String name,
            @JsonProperty(required = true) String email
        ){

        if(!name.matches(RegexDefault.NAME.getPattern()))
            throw new ValidationException(new ErrorResponse("Nome inválido"));

        if(!email.matches(RegexDefault.EMAIL.getPattern()))
            throw new ValidationException(new ErrorResponse("E-mail inválido"));

        //Caberia validação de documentos a depender do tipo de pessoa (Fisica ou Juridica);

        this.name = name;
        this.email = email;
    }
}
