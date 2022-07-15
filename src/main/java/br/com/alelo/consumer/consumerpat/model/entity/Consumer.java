package br.com.alelo.consumer.consumerpat.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "consumer")
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 64, nullable = false)
    private String name;

    @Column(name = "document_number", length = 11, unique = true, nullable = false)
    private String documentNumber;

    @Column(name = "birth_date", columnDefinition = "DATE", nullable = false)
    private LocalDate birthDate;

    @OneToOne(optional = false)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "consumer")
    private Set<Contact> contacts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "consumer")
    private Set<Card> cards = new LinkedHashSet<>();

    @OneToMany(mappedBy = "consumer")
    private Set<Extract> extracts = new LinkedHashSet<>();

}
