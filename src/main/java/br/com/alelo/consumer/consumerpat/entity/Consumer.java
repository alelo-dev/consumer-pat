package br.com.alelo.consumer.consumerpat.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Consumer implements Serializable {

    private static final long serialVersionUID = 1718742452748069777L;
    @Id
    private String idConsumer;
    private String name;
    private String documentNumber;
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @OneToMany(targetEntity=Contact.class,cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "CONSUMER_ID", referencedColumnName = "idConsumer")
    private Set<Contact> contacts;

    //Address
    @OneToMany(targetEntity=Address.class,cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "CONSUMER_ID", referencedColumnName = "idConsumer")
    private Set<Address> addresses;

    //cards
    @OneToMany(targetEntity=Card.class,cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "CONSUMER_ID", referencedColumnName = "idConsumer")
    private Set<Card> cards;
    /*private Long foodCardNumber;
    private BigDecimal foodCardBalance;

    private Long fuelCardNumber;
    private BigDecimal fuelCardBalance;

    private Long drugstoreNumber;
    private BigDecimal drugstoreCardBalance;*/
}
