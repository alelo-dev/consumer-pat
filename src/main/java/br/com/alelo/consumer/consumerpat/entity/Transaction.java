package br.com.alelo.consumer.consumerpat.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@Entity
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Establishment establishment;

    @ManyToOne
    private Card card;

    @Column(name = "BUY_DATE_TIME")
    private LocalDateTime buyDateTime;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "VALUE")
    private double value;


}
