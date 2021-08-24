package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

import br.com.alelo.consumer.consumerpat.dto.v2.CardDTO;
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
@Entity
public class CardEntity {

    // Usaria MapperStruct
    public CardEntity (CardDTO dto){
        this.id = dto.getId();
        this.number = dto.getNumber();
        this.cardBalance = dto.getCardBalance();
        this.type = dto.getType() != null ? dto.getType().getValue() : 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private Integer id;
    private Integer number;
    @EqualsAndHashCode.Exclude
    private BigDecimal cardBalance;
    private Integer type;

    //Default EAGER
    @ManyToOne
    @EqualsAndHashCode.Exclude
    private ConsumerEntity consumerEntity;
    
}
