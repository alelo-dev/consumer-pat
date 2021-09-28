package br.com.alelo.consumer.consumerpat.domain.dto.v2;

import br.com.alelo.consumer.consumerpat.domain.entity.CardEntity;
import br.com.alelo.consumer.consumerpat.domain.entity.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {

    private int id;
    private int cardNumber;
    private BigDecimal cardBalance;
    private CardType cardType;

    public CardDTO(CardEntity cardEntity) {
    }

    public static Set<CardDTO> convertEntityToDto(Set<CardEntity> cardEntity){
        Set<CardDTO> cardsDTO = new HashSet<>();
        if(cardEntity != null){
            cardEntity.forEach(c -> {
                CardDTO cardDTO = new CardDTO();
                cardDTO.setId(c.getId());
                cardDTO.setCardNumber(c.getNumber());
                cardDTO.setCardBalance(c.getCardBalance());
                cardDTO.setCardType(c.getType());
                cardsDTO.add(cardDTO);
            });

            return cardsDTO;
        }
        return Collections.emptySet();
    }
}
