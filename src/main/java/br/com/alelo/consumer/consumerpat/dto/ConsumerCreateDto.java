package br.com.alelo.consumer.consumerpat.dto;
import br.com.alelo.consumer.consumerpat.validator.DateTimeValid;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class ConsumerCreateDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Document number is required")
    private Long documentNumber;

    @NotBlank(message = "Birth date is required")
    @DateTimeValid(format = "yyyy-MM-dd", message="Birth date invalid")
    private String birthDate;

    //contacts
    @NotNull(message = "Mobile Phone number is required")
    private Long mobilePhoneNumber;

    @NotNull(message = "Residence Phone number is required")
    private Long residencePhoneNumber;

    @NotNull(message = "Phone number is required")
    private Long phoneNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Email invalid",
    regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    private String email;

    //Address
    @NotBlank(message = "Street is required")
    private String street;

    @NotNull(message = "Number is required")
    private Integer number;

    @NotBlank(message = "City is required")
    @Pattern(regexp = "^[^0-9]+$", message = "City invalid")
    private String city;

    @NotBlank(message = "Country is required")
    @Pattern(regexp = "^[^0-9]+$", message = "Country invalid")
    private String country;

    @NotNull(message = "Postal code is required")
    private Integer postalCode;

    //cards
    @Positive(message = "Food Card number should be greater than 1")
    private Long foodCardNumber;

    @Positive(message = "Fuel Card number should be greater than 1")
    private Long fuelCardNumber;

    @Positive(message = "Drugstore Card number should be greater than 1")
    private Long drugstoreNumber;

}