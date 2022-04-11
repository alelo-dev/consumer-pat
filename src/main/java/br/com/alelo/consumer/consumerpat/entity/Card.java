package br.com.alelo.consumer.consumerpat.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Card implements Serializable{

    private static final long serialVersionUID = 2534234152419104286L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private UUID idCard;
    
    private Long cardNumber;
    
    @Setter(AccessLevel.NONE)
    private BigDecimal cardBalance;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(referencedColumnName="idTypeCard", name=" ID_TYPE_CARD_CONSUMER", nullable = false)
    private TypeCard typeCard;
    
    public BigDecimal addBalance(BigDecimal value) {
    	return this.cardBalance = value;
    }
    
}
