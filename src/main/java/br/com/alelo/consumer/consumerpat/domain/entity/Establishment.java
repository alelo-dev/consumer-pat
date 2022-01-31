package br.com.alelo.consumer.consumerpat.domain.entity;

import br.com.alelo.consumer.consumerpat.domain.enumeration.CardType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Establishment extends EntidadeBase<Integer> {

    @Id
    @GeneratedValue
    private Integer id;

    private String establishmentName;

    @Enumerated(EnumType.STRING)
    private CardType cardTypeAccepted;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createDate;

}
