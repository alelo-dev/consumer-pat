package br.com.alelo.consumer.consumerpat.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author renanravelli
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ConsumerJsonRequest", description = "Informa\u00E7\u00F5es para cadastrar um consumer.")
public class ConsumerJsonRequest {

  @ApiModelProperty(name = "name", value = "Name", position = 1)
  private String name;

  @ApiModelProperty(name = "documentNumber", value = "Document number", position = 2)
  private int documentNumber;

  @ApiModelProperty(name = "birthDate", value = "Birth date", position = 3)
  private LocalDate birthDate;

  @ApiModelProperty(name = "mobilePhoneNumber", value = "Mobile phone number", position = 4)
  //contacts
  private int mobilePhoneNumber;

  @ApiModelProperty(name = "residencePhoneNumber", value = "Residence phone number", position = 5)
  private int residencePhoneNumber;

  @ApiModelProperty(name = "phoneNumber", value = "Phone number", position = 6)
  private int phoneNumber;

  @ApiModelProperty(name = "email", value = "Email", position = 7)
  private String email;

  @ApiModelProperty(name = "street", value = "Street", position = 8)
  //Address
  private String street;

  @ApiModelProperty(name = "number", value = "Number", position = 9)
  private int number;

  @ApiModelProperty(name = "city", value = "City", position = 10)
  private String city;

  @ApiModelProperty(name = "country", value = "Country", position = 11)
  private String country;

  @ApiModelProperty(name = "portalCode", value = "Portal code", position = 12)
  private int portalCode;

}
