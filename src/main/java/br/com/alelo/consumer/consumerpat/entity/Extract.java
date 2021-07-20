package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Extract {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private Integer establishmentType;

    private String establishmentName;

    private String productDescription;

    private LocalDate dateBuy;

    private Long cardNumber;

    private BigDecimal value;
}
