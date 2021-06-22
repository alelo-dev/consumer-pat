package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.parameter.PurchaseParameter;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Entity
public class Statement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String establishmentName;
    private String productDescription;
    private String cardNumber;
    private BigDecimal value;

    @CreationTimestamp
    private LocalDateTime dateBuy;

    @ManyToOne
    private Consumer consumer;

    public static Statement fromPurchaseParameter(PurchaseParameter parameter) {
        Statement statement = new Statement();

        Consumer consumer = new Consumer();
        consumer.setId(parameter.getConsumerId());

        statement.setConsumer(consumer);
        statement.setEstablishmentName(parameter.getEstablishmentName());
        statement.setCardNumber(parameter.getCardNumber());
        statement.setProductDescription(parameter.getProductDescription());
        statement.setValue(parameter.getProductValue());

        return statement;
    }
}
