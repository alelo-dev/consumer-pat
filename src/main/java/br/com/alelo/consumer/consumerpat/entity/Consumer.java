package br.com.alelo.consumer.consumerpat.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.OffsetDateTime;
import java.util.List;


@Getter
@Setter
@Entity
public class Consumer extends BaseEntity {

    String name;

    Integer documentNumber;

    OffsetDateTime birthDate;

    @Embedded
    private Address address;

    @JsonIgnoreProperties("consumer")
    @OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL)
    private List<Contact> contacts;

    @JsonIgnoreProperties("consumer")
    @OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL)
    private List<Card> cards;

}
