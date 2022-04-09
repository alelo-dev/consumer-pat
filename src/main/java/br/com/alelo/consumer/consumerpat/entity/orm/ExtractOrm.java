package br.com.alelo.consumer.consumerpat.entity.orm;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@Entity(name = "Extract")
public class ExtractOrm implements Extract {

    //TODO fix mapping orm
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "establishment_name_id")
    private int establishmentNameId;

    @Column(name = "establishment_name")
    private String establishmentName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "date")
    private Date date;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "value")
    private double value;

    ExtractOrm() {} //JPA compatibility

    public ExtractOrm(String establishmentName, String productDescription, String cardNumber, double value) {
        this.date = new Date();
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.cardNumber = cardNumber;
        this.value = value;
    }
}
