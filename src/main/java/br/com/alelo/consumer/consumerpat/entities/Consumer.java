package br.com.alelo.consumer.consumerpat.entities;

import br.com.alelo.consumer.consumerpat.dtos.ConsumerDto;
import br.com.alelo.consumer.consumerpat.enumeraters.ESTABLISHMENT;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class Consumer implements Serializable {

    private static final long serialVersionUID = 972288345818687112L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DOCUMENT_NUMBER")
    private Integer documentNumber;
    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;
    @Embedded
    private Contacts contacts;
    @Embedded
    private Address address;
    @Embedded
    private Cards cards;

    public Consumer(ConsumerDto consumerDto) {
        setConsumerByDto(consumerDto);
    }

    public void setConsumerByDto(ConsumerDto consumerDto) {
        this.id = consumerDto.getId();
        this.name = consumerDto.getName();
        this.documentNumber = consumerDto.getDocumentNumber();
        this.birthDate = consumerDto.getBirthDate();
        this.contacts = buildContact(consumerDto);
        this.address = buildAddress(consumerDto);
        this.cards = buildCars(consumerDto);
    }

    @Transient
    private Contacts buildContact(ConsumerDto consumerDto) {
        return new Contacts(
                consumerDto.getMobilePhoneNumber(),
                consumerDto.getResidencePhoneNumber(),
                consumerDto.getPhoneNumber(),
                consumerDto.getEmail()
        );
    }

    @Transient
    private Address buildAddress(ConsumerDto consumerDto) {
        return new Address(
                consumerDto.getStreet(),
                consumerDto.getNumber(),
                consumerDto.getCity(),
                consumerDto.getCountry(),
                consumerDto.getPortalCode()
        );
    }

    @Transient
    private Cards buildCars(ConsumerDto consumerDto) {
        return new Cards(
                ESTABLISHMENT.valueOf(consumerDto.getEstablishmentType().toString()),
                consumerDto.getFoodCardNumber(),
                consumerDto.getFoodCardBalance(),
                consumerDto.getFuelCardNumber(),
                consumerDto.getFuelCardBalance(),
                consumerDto.getDrugstoreCardNumber(),
                consumerDto.getDrugstoreCardBalance()
        );
    }

}
