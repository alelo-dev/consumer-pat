package br.com.alelo.consumer.consumerpat.integration.rest.controller.dto.request;

import br.com.alelo.consumer.consumerpat.domain.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.entity.Extract;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
public class ExtractRequestPostV1 {

    private Long establishmentNameId;
    private String establishmentName;
    private String productDescription;
    private LocalDate dateBuy;
    private BigDecimal value;
    private String cardCode;

    public static Extract transformToExtract(ExtractRequestPostV1 extractRequestPostV1) {
        return Extract.builder()
                .dateBuy(extractRequestPostV1.getDateBuy())
                .establishmentNameId(extractRequestPostV1.getEstablishmentNameId())
                .establishmentName(extractRequestPostV1.getEstablishmentName())
                .value(extractRequestPostV1.getValue())
                .productDescription(extractRequestPostV1.getProductDescription())
                .cards(Set.of(Card.builder().cardCode(extractRequestPostV1.getCardCode()).build()))
                .build();
    }

}
