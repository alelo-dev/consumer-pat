package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.entity.enums.EstablishmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="TB_EXTRACT")
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(value = EnumType.ORDINAL)
    private EstablishmentType establishment;

    private String productDescription;
    private Date dateBuy;
    private Double value;

    @ManyToOne
    @JoinColumn(name = "CARD_ID")
    private Card card;
}
