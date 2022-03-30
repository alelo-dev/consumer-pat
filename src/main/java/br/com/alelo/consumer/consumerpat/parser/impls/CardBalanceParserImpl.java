package br.com.alelo.consumer.consumerpat.parser.impls;

import br.com.alelo.consumer.consumerpat.dto.BalanceDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.parser.interfaces.CardBalanceParser;
import org.springframework.stereotype.Component;


/**
 * OBSERVAÇÃO: Nestes casos de fazer parse de objetos DTOs para entidades e vice-versa, eu utilizaria o
 * @Mapper de org.mapstruct.Mapper, porém, como requisito deste projeto de teste pede para que não
 * inclua qualquer lib, precisei fazer desta forma implementada abaixo.
 */
@Component
public class CardBalanceParserImpl implements CardBalanceParser {
    @Override
    public BalanceDTO parse(Card card) {
        return BalanceDTO.builder()
                .cardNumber(card.getNumber())
                .value(card.getBalance())
                .build();
    }
}
