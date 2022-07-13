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

  @ApiModelProperty(name = "foodCardNumber", value = "Food card number", position = 13)
  //cards
  private int foodCardNumber;

  @ApiModelProperty(name = "foodCardBalance", value = "Food card balance", position = 14)
  private BigDecimal foodCardBalance;

  @ApiModelProperty(name = "fuelCardNumber", value = "Fuel card number", position = 15)
  private int fuelCardNumber;

  @ApiModelProperty(name = "fuelCardBalance", value = "Fuel card balance", position = 16)
  private BigDecimal fuelCardBalance;

  @ApiModelProperty(name = "drugstoreNumber", value = "Drugstore card number", position = 17)
  private int drugstoreNumber;

  @ApiModelProperty(name = "drugstoreCardBalance", value = "Drugstore card balance", position = 18)
  private BigDecimal drugstoreCardBalance;

  public Consumer toEntity() {
    return new Consumer(null, this.name, this.documentNumber, this.birthDate,
        this.mobilePhoneNumber, this.residencePhoneNumber, this.phoneNumber, this.email,
        this.street, this.number, this.city, this.country, this.portalCode, this.foodCardNumber,
        this.foodCardBalance, this.fuelCardNumber, this.fuelCardBalance, this.drugstoreNumber,
        this.drugstoreCardBalance);
  }

}
