package br.com.alelo.consumer.consumerpat.helper;

import br.com.alelo.consumer.consumerpat.domain.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.entity.Extract;

import java.time.LocalDate;
import java.util.Set;

public class ExtractHelper {

    public static Extract buildExtract(Card card, Double value) {
        return Extract.builder()
                .cards(Set.of(card))
                .productDescription("alimentos")
                .value(value)
                .establishmentName("Estabelecimento do joao")
                .dateBuy(LocalDate.now())
                .establishmentNameId(123L)
                .build();
    }

}
