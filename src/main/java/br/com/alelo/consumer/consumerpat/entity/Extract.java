package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.entity.card.Card;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_card")
    private Card card;

    @ManyToOne
    @JoinColumn(name = "establishment")
    private Establishment establishment;

    @NonNull
    private BigDecimal value;

    @NonNull
    private String productDescription;

    @NonNull
    private LocalDate dateBuy;

}
