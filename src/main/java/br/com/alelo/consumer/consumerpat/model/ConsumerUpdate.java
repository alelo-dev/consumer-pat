package br.com.alelo.consumer.consumerpat.model;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerUpdate {
    @NotNull
    private Integer consumerId;
    @NotNull
    private String consumerName;

    @NotNull
    private int documentNumber;

    @NotNull
    private Date birthDate;

    @NotNull
    private int mobilePhoneNumber;

    @NotNull
    private int residencePhoneNumber;

    @NotNull
    private int phoneNumber;

    @NotNull
    @Email
    @Schema(name = "email", example = "string@string.com", required = true)
    private String email;

    @NotNull
    private String street;

    @NotNull
    private int number;

    @NotNull
    private String city;

    @NotNull
    private String country;

    @NotNull
    private int portalCode;

    @NotNull
    private int foodCardNumber;

    @NotNull
    private int fuelCardNumber;

    @NotNull
    private int drugstoreCardNumber;

    public Consumer toEntity() {
        return new Consumer(
                this.consumerName,
                this.documentNumber,
                this.birthDate,
                this.mobilePhoneNumber,
                this.residencePhoneNumber,
                this.phoneNumber,
                this.email,
                this.street,
                this.number,
                this.city,
                this.country,
                this.portalCode,
                this.foodCardNumber,
                this.fuelCardNumber,
                this.drugstoreCardNumber
        );
    }
}
