package br.com.alelo.consumer.pat.entity;

import br.com.alelo.consumer.pat.domain.EstablishmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EstablishmentType establishmentType;

    private String cardNumber;

    private BigDecimal balance;

    private LocalDateTime rechargedAt;

    public static Card fromEstablishmentType(EstablishmentType establishmentType) {
        return Card.builder()
            .cardNumber(UUID.randomUUID().toString()) // Fake cardNumber - just for this test
            .balance(BigDecimal.ZERO)
            .establishmentType(establishmentType)
            .rechargedAt(LocalDateTime.now())
            .build();
    }

}
