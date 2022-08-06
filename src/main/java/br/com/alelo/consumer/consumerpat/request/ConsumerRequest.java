package br.com.alelo.consumer.consumerpat.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ConsumerRequest {

    @ApiModelProperty(required = true, notes = "Consumer name", example = "RODRIGO BEKER DA COSTA", dataType = "java.lang.String")
    @NotBlank
    @Size(min = 1, max = 255)
    private String name;

    @ApiModelProperty(required = true, notes = "Document Number of Consumer", example = "000.000.000-00", dataType = "java.lang.String")
    @NotBlank
    @Pattern(regexp ="^(((\\d{3}).(\\d{3}).(\\d{3})-(\\d{2}))?((\\d{2}).(\\d{3}).(\\d{3})/(\\d{4})-(\\d{2}))?)*$", message = "The cpf must be valid")
    private String documentNumber;

    @ApiModelProperty(required = true, notes = "Consumer birthDate. Accepts date in ISO format YYYY-MM-DD", example = "1993-12-23")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate birthDate;

    @ApiModelProperty(required = true, notes = "Consumer mobile phone number", example = "(73) 99971-3718", dataType = "java.lang.String")
    @NotBlank
    @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$", message = "The mobile number must be valid")
    private String mobilePhoneNumber;

    @ApiModelProperty(required = true, notes = "Consumer residence phone number", example = "(73) 3281-1848", dataType = "java.lang.String")
    @NotBlank
    @Pattern(regexp = "^((\\+\\d{2}\\s)?\\(\\d{2}\\)\\s?\\d{4}\\d?\\-\\d{4})?$", message = "The residence phone number must be valid")
    private String residencePhoneNumber;

    @ApiModelProperty(required = true, notes = "Consumer email", dataType = "java.lang.String", example = "rodrigobeker18@gmail.com")
    @Email(regexp = "^.+@gmail.com$", message = "The email must be a gmail")
    private String email;

    @ApiModelProperty(required = true, notes = "Street Name", example = "RUA CEDRO, COLONIAL", dataType = "java.lang.String")
    @NotBlank
    @Size(min = 1, max = 255)
    private String street;

    @ApiModelProperty(required = true, notes = "number", example = "20", dataType = "java.lang.Integer")
    @NotNull
    private Integer numberResidency;

    @ApiModelProperty(required = true, notes = "City name", example = "EUNÁPOLIS", dataType = "java.lang.String")
    @NotBlank
    @Size(min = 1, max = 255)
    private String city;

    @ApiModelProperty(required = true, notes = "Country name", example = "BRASIL", dataType = "java.lang.String")
    @NotBlank
    @Size(min = 1, max = 255)
    private String country;

    @ApiModelProperty(required = true, notes = "Consumer residence phone number", example = "45821-419", dataType = "java.lang.String")
    @NotBlank
    @Pattern(regexp = "^[0-9]{5}(?:-[0-9]{3})?$", message = "The residence phone number must be valid")
    private String postalCode;
}
