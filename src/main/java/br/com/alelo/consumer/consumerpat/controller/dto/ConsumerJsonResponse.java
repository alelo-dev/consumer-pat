package br.com.alelo.consumer.consumerpat.controller.dto;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
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
@ApiModel(value = "ConsumerJsonResponse", description = "Informa\u00E7\u00F5es de um consumer.")
public class ConsumerJsonResponse {

  @ApiModelProperty(name = "id", value = "Id", position = 1)
  private Integer id;

  @ApiModelProperty(name = "name", value = "Name", position = 2)
  private String name;

  @ApiModelProperty(name = "documentNumber", value = "Document number", position = 3)
  private int documentNumber;

  @ApiModelProperty(name = "birthDate", value = "Birth date", position = 4)
  private LocalDate birthDate;

  @ApiModelProperty(name = "mobilePhoneNumber", value = "Mobile phone number", position = 5)
  //contacts
  private int mobilePhoneNumber;

  @ApiModelProperty(name = "residencePhoneNumber", value = "Residence phone number", position = 6)
  private int residencePhoneNumber;

  @ApiModelProperty(name = "phoneNumber", value = "Phone number", position = 7)
  private int phoneNumber;

  @ApiModelProperty(name = "email", value = "Email", position = 8)
  private String email;

  @ApiModelProperty(name = "street", value = "Street", position = 9)
  //Address
  private String street;

  @ApiModelProperty(name = "number", value = "Number", position = 10)
  private int number;

  @ApiModelProperty(name = "city", value = "City", position = 11)
  private String city;

  @ApiModelProperty(name = "country", value = "Country", position = 12)
  private String country;

  @ApiModelProperty(name = "portalCode", value = "Portal code", position = 13)
  private int portalCode;

}
