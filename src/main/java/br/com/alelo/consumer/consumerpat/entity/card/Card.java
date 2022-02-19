package br.com.alelo.consumer.consumerpat.entity.card;

import br.com.alelo.consumer.consumerpat.entity.consumer.Consumer;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Card {

    @Id
    private Long number;
    @NonNull
    private BigDecimal balance;
    @NonNull
    private Long type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consumer")
    private Consumer consumer;
}
