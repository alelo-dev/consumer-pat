package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.entity.enumeration.CardType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Establishment {

    @Id
    @GeneratedValue
    private int id;

    private String establishmentName;

    @Enumerated(EnumType.STRING)
    private CardType cardTypeAccepted;


}
