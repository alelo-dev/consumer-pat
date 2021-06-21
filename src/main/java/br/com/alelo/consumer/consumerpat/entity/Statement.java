package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Entity
public class Statement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String productDescription;
    private String cardNumber;
    private BigDecimal value;

    @CreationTimestamp
    private LocalDateTime dateBuy;

    @ManyToOne
    private Consumer consumer;

    @ManyToOne
    private Establishment establishment;
}
