package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 200, nullable = false)
    private String productDescription;

    @Column(nullable = false)
    private Date dateBuy;

    @Column(precision = 15, scale = 2, nullable = false)
    private Double value;

    @ManyToOne
    @JoinColumn(name = "ESTABLISHMENT_ID", referencedColumnName = "ID")
    private Establishment establishment;

    @ManyToOne
    @JoinColumn(name = "CARD_ID", referencedColumnName = "ID")
    private Card card;

}
