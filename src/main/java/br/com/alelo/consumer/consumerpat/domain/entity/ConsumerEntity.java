package br.com.alelo.consumer.consumerpat.domain.entity;


import br.com.alelo.consumer.consumerpat.domain.dto.v2.ConsumerDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CONSUMER")
public class ConsumerEntity {

    public ConsumerEntity(ConsumerDTO dto) {

        if (dto != null) {
            this.id = dto.getId();
            this.name = dto.getName();
            this.documentNumber = dto.getDocumentNumber();
            this.birthDate = dto.getBirthDate();
            this.contactEntity = new ContactEntity(dto.getContactDTO());
            this.addressEntity = new AddressEntity(dto.getAddressDTO());
            this.cards = dto.getCards() != null && ! dto.getCards().isEmpty()
                    ? dto.getCards().stream().map(CardEntity::new).collect(Collectors.toSet())
                    : null;
        }

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer documentNumber;
    private LocalDate birthDate;

    // contacts
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    private ContactEntity contactEntity;

    // Address
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    private AddressEntity addressEntity;

    // cards
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "consumer_id")
    private Set<CardEntity> cards;

}
