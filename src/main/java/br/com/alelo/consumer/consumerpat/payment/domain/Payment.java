package br.com.alelo.consumer.consumerpat.payment.domain;

import br.com.alelo.consumer.consumerpat.common.domain.Card;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Embedded
    private Establishment establishment;

    private String description;

    @ManyToOne
    private Card card;

    private BigDecimal amount;

    private LocalDate date;
}
