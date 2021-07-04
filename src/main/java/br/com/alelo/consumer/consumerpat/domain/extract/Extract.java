package br.com.alelo.consumer.consumerpat.domain.extract;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Entity
@NoArgsConstructor
@Table(name = "extract")
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "establishment_id")
    private int establishmentId;
    @Column(name = "establishment_name")
    private String establishmentName;
    @Column(name = "product_description")
    private String productDescription;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "value")
    private double value;

    @LastModifiedDate
    @Column(name = "date_Buy")
    private LocalDateTime dateBuy;




    public Extract(String establishmentName, String productDescription, String cardNumber, double value) {
        this.establishmentId = establishmentId;
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = LocalDateTime.now();
        this.cardNumber = cardNumber;
        this.value = value;
    }
}
