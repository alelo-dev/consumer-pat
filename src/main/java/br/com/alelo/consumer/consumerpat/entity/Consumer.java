package br.com.alelo.consumer.consumerpat.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(insertable = false, updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    //alterado para String caso exista algum caso que inicie com 0
    //ou queira começar a salvar usando alguma máscara de documento
    @Column(length = 30, nullable = false)
    private String documentNumber;

    private Date birthDate;

    //contacts
    private String mobilePhoneNumber;

    private String residencePhoneNumber;

    private String phoneNumber;

    private String email;

    //Address
    private String street;

    private String number;

    private String city;

    private String country;

    private int portalCode;

    //cards
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CONSUMER_ID", referencedColumnName = "ID")
    private List<Card> cards;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return Objects.equals(documentNumber, consumer.documentNumber)
                && Objects.equals(id, consumer.id) && Objects.equals(name, consumer.name);
    }


}
