package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.entity.constants.LengthFieldsBD;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
public class Extract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ESTABLISHMENT_ID", nullable = false)
    private Establishment establishment;

    @Column(name = "PRODUCT_DESCRIPTION", nullable = false, length = LengthFieldsBD.LENGTH_100)
    private String productDescription;

    @Column(name = "DATE_BUY", nullable = false)
    private LocalDateTime dateBuy;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CARD_NUMBER", nullable = false)
    private Card card;

    @Column(name = "VALUE", nullable = false)
    private Double value;
}
