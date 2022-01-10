package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Extract implements Serializable {

    private static final long serialVersionUID = -1450407979607841520L;
    @Id
    private String idExtract;
    @ManyToOne
    private Establishment stablishment;
    private String productDescription;
    @Temporal(TemporalType.DATE)
    private Date dateBuy;
    @ManyToOne
    private Card card;
    private BigDecimal value;
}
