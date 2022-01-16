package br.com.alelo.consumer.consumerpat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cards")
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private BigInteger number;

    @Column(nullable = false)
    private CardType type;

    @Column(nullable = false)
    private BigDecimal funds;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;

}
