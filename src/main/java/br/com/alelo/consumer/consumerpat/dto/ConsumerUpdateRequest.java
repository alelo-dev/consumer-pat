package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.contants.RegexDefault;
import br.com.alelo.consumer.consumerpat.exception.ValidationException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Data
public class ConsumerUpdateRequest {

    @JsonProperty(required = true)
    private Integer id;
    private String name;
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
    public ConsumerUpdateRequest(
            @JsonProperty String name,
            @JsonProperty String email
    ){

        if(Objects.nonNull(name) && !name.matches(RegexDefault.NAME.getPattern()))
            throw new ValidationException(new ErrorResponse("Nome inválido"));

        if(Objects.nonNull(email) && !email.matches(RegexDefault.EMAIL.getPattern()))
            throw new ValidationException(new ErrorResponse("E-mail inválido"));

        //Caberia validação de documentos a depender do tipo de pessoa (Fisica ou Juridica);

        this.name = name;
        this.email = email;
    }

}
