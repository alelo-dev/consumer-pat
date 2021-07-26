package br.com.alelo.consumer.consumerpat.model;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.alelo.consumer.consumerpat.exception.CardInvalidException;
import br.com.alelo.consumer.consumerpat.model.type.EstablishmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "extract")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Transaction {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Consumer consumer;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Card card;
    
    @Column(nullable = false)
    private String confirmationCode;
    
    @Column(nullable = false, length = 10)
    @Enumerated
    private EstablishmentType establishmentType;
    
    @Column(nullable = false, length = 300)
    private String establishmentName;
    
    @Column(nullable = false, length = 600)
    private String productDescription;
    
    private Instant dateBuy;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal value;

    public void checkEstablishmentType() {
        if (!establishmentType.name().equals(card.getType().name())) {
            throw new CardInvalidException();
        }

    }
    
}
