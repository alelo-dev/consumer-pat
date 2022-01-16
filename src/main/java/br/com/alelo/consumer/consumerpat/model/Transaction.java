package br.com.alelo.consumer.consumerpat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "establishment_name", nullable = false)
    private String establishmentName;

    @Column(name = "product_description", nullable = false)
    private String productDescription;

    @Column(name = "purchase_date_time", nullable = false)
    private LocalDateTime purchaseDateTime;

    @Column(name = "card_number", nullable = false)
    private BigInteger cardNumber;

    @Column(nullable = false)
    private BigDecimal value;

}
