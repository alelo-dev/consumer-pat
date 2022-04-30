package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long establishmentNameId;
    private String establishmentName;
    private String productDescription;
    @Temporal(TemporalType.DATE)
    private Date dateBuy;
    private Long cardNumber;
    @Column(precision = 20, scale = 2)
    private BigDecimal value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;

    public Extract(Long id, Long establishmentNameId, String establishmentName, String productDescription, Date dateBuy, Long cardNumber, BigDecimal value) {
        this.id = id;
        this.establishmentNameId = establishmentNameId;
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public Extract( String productDescription, Date dateBuy, Long cardNumber, BigDecimal value) {
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public Extract(String establishmentName, String productDescription, Date dateBuy, Long cardNumber, BigDecimal value) {
        this.establishmentNameId = establishmentNameId;
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public Extract(String establishmentName, String productDescription, Date dateBuy, Long cardNumber, BigDecimal value, Consumer consumer) {
        this.establishmentNameId = establishmentNameId;
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
        this.consumer = consumer;
    }
}
