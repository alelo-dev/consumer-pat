package br.com.alelo.consumer.consumerpat.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@RequiredArgsConstructor
@ToString
@Entity
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column
    Integer establishmentNameId;

    @Column
    String establishmentName;

    @Column
    String productDescription;

    @Column
    LocalDate dateBuy;

    @Column
    Integer cardNumber;

    @Column
    BigDecimal amount;

}
