package br.com.alelo.consumer.consumerpat.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="Consumer", description="Model class for consumers")
public class ConsumerDto {

    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "name")
    @ApiModelProperty(value = "Consumer's name")
    private String name;

    @JsonProperty(value = "documentNumber")
    @ApiModelProperty(value = "Consumer's document number")
    private String documentNumber;

    @JsonProperty(value = "birtheDate")
    @ApiModelProperty(value = "Consumer's date of birth")
    private LocalDate birthDate;

    @JsonProperty(value = "contact")
    @ApiModelProperty(value = "Consumer's contact information")
    private ContactDto contact;

    @JsonProperty(value = "address")
    @ApiModelProperty(value = "Consumer's address information")
    private AddressDto address;
}
