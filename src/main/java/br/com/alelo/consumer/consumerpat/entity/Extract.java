package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(insertable = false, updatable = false, nullable = false)
    private Long id;

    private String productDescription;

    private Date dateBuy;

    @Column(precision = 15, scale = 2)
    private double value;

    @ManyToOne
    @JoinColumn(name = "ESTABLISHMENT_ID", referencedColumnName = "ID")
    private Establishment establishment;

    @ManyToOne
    @JoinColumn(name = "CARD_ID", referencedColumnName = "ID")
    private Card card;

}
