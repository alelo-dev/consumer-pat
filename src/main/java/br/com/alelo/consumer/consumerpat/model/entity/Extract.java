package br.com.alelo.consumer.consumerpat.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "extract")
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "establishment_id", nullable = false)
    private Long establishmentId;

    @Column(name = "establishment_name", length = 64, nullable = false)
    private String establishmentName;

    @Column(name = "product_description", length = 128, nullable = false)
    private String productDescription;

    @Column(name = "date_buy", nullable = false)
    private LocalDateTime dateBuy;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @ManyToOne(optional = false)
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    @ManyToOne(optional = false)
    @JoinColumn(name = "consumer_id", nullable = false)
    private Consumer consumer;

}
