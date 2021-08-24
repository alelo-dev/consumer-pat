package br.com.alelo.consumer.consumerpat.dto.v2;

import java.math.BigDecimal;

import br.com.alelo.consumer.consumerpat.entity.CardEntity;
import br.com.alelo.consumer.consumerpat.enuns.CardTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {

    // Usaria MapperStruct
    public CardDTO(CardEntity entity) {
        this.id = entity.getId();
        this.number = entity.getNumber();
        this.cardBalance = entity.getCardBalance();
        this.type = entity.getType();

    }

    @EqualsAndHashCode.Exclude
    private Integer id;
    private Integer number;
    @EqualsAndHashCode.Exclude
    private BigDecimal cardBalance;
    private CardTypeEnum type;

}
