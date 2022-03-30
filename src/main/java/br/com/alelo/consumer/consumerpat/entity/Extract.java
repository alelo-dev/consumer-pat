package br.com.alelo.consumer.consumerpat.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int establishmentNameId;
    private String establishmentName;
    private String productDescription;
    private LocalDate dateBuy;
    private Long cardNumber;
    private BigDecimal value;
}
