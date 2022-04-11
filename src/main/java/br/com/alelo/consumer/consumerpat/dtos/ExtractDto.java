package br.com.alelo.consumer.consumerpat.dtos;

import br.com.alelo.consumer.consumerpat.models.enums.EstablishmentEnum;
import lombok.Data;

import java.util.Date;

@Data
public class ExtractDto {

    private EstablishmentEnum establishmentEnum;
    private String establishmentName;
    private String productDescription;
    private Date dateBuy;
    private int cardNumber;
    private double value;

}
