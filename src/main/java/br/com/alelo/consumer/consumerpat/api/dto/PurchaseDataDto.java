package br.com.alelo.consumer.consumerpat.api.dto;

import br.com.alelo.consumer.consumerpat.domain.enumeration.EstablishmentType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Classe que modela os dados de produtos adquiridos pelo consumidor
 * em estabelecimentos conveniados.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Data
public class PurchaseDataDto extends Dto {

    private EstablishmentType establishmentType;
    private String establishmentName;
    private BigInteger cardNumber;
    private String productDescription;
    private BigDecimal value;

    public static boolean isFoodEstablishmentType(PurchaseDataDto dto) {
        return EstablishmentType.isFoodEstablishment(dto.getEstablishmentType());
    }

    public static boolean isDrugstoreEstablishmentType(PurchaseDataDto dto) {
        return EstablishmentType.isDrugstoreEstablishment(dto.getEstablishmentType());
    }

    public static boolean isFuelEstablishmentType(PurchaseDataDto dto) {
        return EstablishmentType.isFuelEstablishment(dto.getEstablishmentType());
    }

}
