package br.com.alelo.consumer.consumerpat.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="TB_CONSUMER")
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer documentNumber;
    private Date birthDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn( name = "CONTACT_ID" )
    private Contact contact;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn( name = "ADDRESS_ID" )
    private Address address;

    @OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards;

}
