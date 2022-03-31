package br.com.alelo.consumer.consumerpat.entity;



import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "consumer")
public class Consumer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String documentNumber;
    private LocalDate birthDate;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn( name = "contact_id" )
    private Contact contact;

    @OneToOne(cascade=CascadeType.ALL)

    @JoinColumn( name = "address_id" )
    private Address address;

    @OneToMany(mappedBy="consumer")
    private List<Card> cards;

}
