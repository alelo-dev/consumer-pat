package br.com.alelo.consumer.consumerpat.data.entity;


import br.com.alelo.consumer.consumerpat.domain.Industry;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;


@Data
@Entity
@Accessors(chain = true)
public class Card {
    @Id
    String number;
    Integer consumerId;
    BigDecimal balance;
    Industry type;
}
