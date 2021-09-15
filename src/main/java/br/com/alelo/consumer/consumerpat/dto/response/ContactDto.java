package br.com.alelo.consumer.consumerpat.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="Contact", description="Model class for contact information")
public class ContactDto {

    @JsonProperty(value = "mobilePhoneNumber")
    @ApiModelProperty(value = "Mobile phone number")
    private int mobilePhoneNumber;

    @JsonProperty(value = "residencePhoneNumber")
    @ApiModelProperty(value = "Residencial phone number")
    private int residencePhoneNumber;

    @JsonProperty(value = "phoneNumber")
    @ApiModelProperty(value = "Other phone number")
    private int phoneNumber;

    @JsonProperty(value = "email")
    @ApiModelProperty(value = "E-mail address")
    private String email;
}
