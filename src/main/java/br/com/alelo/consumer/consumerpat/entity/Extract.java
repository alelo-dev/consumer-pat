package br.com.alelo.consumer.consumerpat.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //    private Long establishmentId;
    private String establishmentName;
    private String productDescription;
    private BigDecimal value;
    private LocalDateTime dateBuy;
    private int amount;

    public Extract value(BigDecimal value) {
        this.value = value.multiply(new BigDecimal(amount));
        return this;
    }
}