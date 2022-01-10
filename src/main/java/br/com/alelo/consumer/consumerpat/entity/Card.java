package br.com.alelo.consumer.consumerpat.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Card implements Serializable {

    private static final long serialVersionUID = 5030669044273546555L;
    @Id
    private String idCard;
    private Long cardNumber;
    private BigDecimal cardBalance;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(referencedColumnName="idTypeCard", name=" ID_TYPE_CARD_CONSUMER", nullable = false)
    private TypeCard typeCard;

    public void setAddBalance(BigDecimal cardBalance){
        this.cardBalance = this.cardBalance.add(cardBalance);
    }

    public Boolean setSubtractBalance(BigDecimal value){
        if(this.getCardBalance().subtract(value).compareTo(BigDecimal.ZERO) > 0){
            this.cardBalance = this.cardBalance.subtract(value);
            return true;
        }
        return false;
    }

    public Boolean isBalance(BigDecimal value){
        if(this.getCardBalance().subtract(value).compareTo(BigDecimal.ZERO) > 0){
            return true;
        }
        return false;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setTypeCard(TypeCard typeCard) {
        this.typeCard = typeCard;
    }
}
