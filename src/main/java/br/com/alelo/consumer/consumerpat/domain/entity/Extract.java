package br.com.alelo.consumer.consumerpat.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * Classe que modela extratos de transações realizadas por um consumidor em estabelecimentos conveniados.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    private BigInteger establishmentId;
    private String establishmentName;
    private String productDescription;
    private Date dateBuy;
    private BigInteger cardNumber;
    private BigDecimal value;

}
