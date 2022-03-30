package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.entity.constants.LengthFieldsBD;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "CONSUMER")
public class Consumer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME", nullable = false, length = LengthFieldsBD.LENGTH_200)
    private String name;

    @Column(name = "DOCUMENT_NUMBER", nullable = false)
    private Integer documentNumber;

    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;

    @OneToOne(mappedBy = "consumer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Contact contact;

    @OneToOne(mappedBy = "consumer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "consumer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Card> cards;
}
