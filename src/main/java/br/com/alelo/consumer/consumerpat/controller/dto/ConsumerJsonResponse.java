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

  @ApiModelProperty(name = "foodCardNumber", value = "Food card number", position = 14)
  //cards
  private int foodCardNumber;

  @ApiModelProperty(name = "foodCardBalance", value = "Food card balance", position = 15)
  private BigDecimal foodCardBalance;

  @ApiModelProperty(name = "fuelCardNumber", value = "Fuel card number", position = 16)
  private int fuelCardNumber;

  @ApiModelProperty(name = "fuelCardBalance", value = "Fuel card balance", position = 17)
  private BigDecimal fuelCardBalance;

  @ApiModelProperty(name = "drugstoreNumber", value = "Drugstore card number", position = 18)
  private int drugstoreNumber;

  @ApiModelProperty(name = "drugstoreCardBalance", value = "Drugstore card balance", position = 19)
  private BigDecimal drugstoreCardBalance;

  public static ConsumerJsonResponse fromEntity(Consumer consumer) {
    return new ConsumerJsonResponse(consumer.getId(), consumer.getName(),
        consumer.getDocumentNumber(), consumer.getBirthDate(),
        consumer.getMobilePhoneNumber(), consumer.getResidencePhoneNumber(),
        consumer.getPhoneNumber(), consumer.getEmail(),
        consumer.getStreet(), consumer.getNumber(), consumer.getCity(), consumer.getCountry(),
        consumer.getPortalCode(), consumer.getFoodCardNumber(),
        consumer.getFoodCardBalance(), consumer.getFuelCardNumber(), consumer.getFuelCardBalance(),
        consumer.getDrugstoreNumber(),
        consumer.getDrugstoreCardBalance());
  }

}
