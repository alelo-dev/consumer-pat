package br.com.alelo.consumer.consumerpat.entity;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 24/07/2021 | 12:09
 */

import br.com.alelo.consumer.consumerpat.model.enums.AleloCardTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerAleloCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private AleloCardTypeEnum type;
    private String number;
    private BigDecimal balance;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;

    public ConsumerAleloCard(AleloCardTypeEnum type, String number, BigDecimal balance) {
        this.type = type;
        this.number = number;
        this.balance = balance;
    }
}
