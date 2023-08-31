package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.entity.enums.CompanyType;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(value = EnumType.ORDINAL)
    private CompanyType company;

    private String productDescription;
    private Date dateBuy;
    private Double value;

    @ManyToOne
    @JoinColumn(name = "CARD_ID")
    private Card card;

}
