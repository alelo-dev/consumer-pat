package br.com.alelo.consumer.consumerpat.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * Classe de objetos de transporte de dados do extrato das transações realizadas
 * entre o consumidor e os estabelecimentos conveniados.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Data
public class ExtractDto extends Dto {

    private BigInteger id;
    private BigInteger establishmentId;
    private String establishmentName;
    private String productDescription;
    private Date dateBuy;
    private BigInteger cardNumber;
    private BigDecimal value;

    // TODO - avaliar se cria um converter para ser injetado no Controller.
    public static ExtractDto convertToExtractDto(PurchaseDataDto purchaseDataDto) {

        ExtractDto extractDto = null;

        if (purchaseDataDto != null) {
            extractDto = new ExtractDto();
            extractDto.setEstablishmentName(purchaseDataDto.getEstablishmentName());
            extractDto.setProductDescription(purchaseDataDto.getProductDescription());
            extractDto.setDateBuy(new Date());
            extractDto.setCardNumber(purchaseDataDto.getCardNumber());
            extractDto.setValue(purchaseDataDto.getValue());
        }

        return extractDto;
    }

}
