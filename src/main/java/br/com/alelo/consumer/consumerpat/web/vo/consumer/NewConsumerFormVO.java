package br.com.alelo.consumer.consumerpat.web.vo.consumer;

import br.com.alelo.consumer.consumerpat.utils.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewConsumerFormVO implements Serializable {

    // Consumer
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Document cannot be null")
    @CPF(message = "Document is not valid (CPF)")
    private String documentNumber;

    @NotNull(message = "BirthDate cannot be null")
    @JsonFormat(pattern = Constants.DATE_FORMAT_PATTERN)
    @DateTimeFormat(pattern = Constants.DATE_FORMAT_PATTERN)
    private LocalDate birthDate;

    // Address
    @NotNull(message = "Street cannot be null")
    @NotBlank(message = "Street cannot be null")
    private String street;

    @NotNull(message = "Number cannot be null")
    private Integer number;

    @NotNull(message = "City cannot be null")
    @NotBlank(message = "City cannot be null")
    private String city;

    @NotNull(message = "Country cannot be null")
    @NotBlank(message = "Country cannot be null")
    private String country;

    @NotNull(message = "PostalCode cannot be null")
    private Long postalCode;

    // Contacts
    private String email;
    private String phone;
    private String mobilePhone;
    private String residencePhone;

    // Cards
    private BigDecimal foodCardBalance;
    private BigDecimal drugstoreCardBalance;
    private BigDecimal fuelCardBalance;

}
