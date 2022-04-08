package br.com.alelo.consumer.consumerpat.entity.orm;


import br.com.alelo.consumer.consumerpat.entity.Consumer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Setter
@Getter
@Entity(name = "consumer")
public class ConsumerORM implements Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    @Column(name = "document_number")
    private int documentNumber;

    @Column(name = "birth_date")
    private Date birthDate;

    @OneToOne
    private ConsumerContactsORM contacts;

    @OneToOne
    private AddressORM address;

    //TODO unify cards
    @OneToOne
    private CardORM foodCard;

    @OneToOne
    private CardORM fuelCard;

    @OneToOne
    private CardORM drugstoreCard;

}
