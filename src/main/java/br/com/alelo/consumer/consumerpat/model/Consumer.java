package br.com.alelo.consumer.consumerpat.model;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "consumer", uniqueConstraints = @UniqueConstraint(columnNames = "documentNumber"))
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long      id;
    private String    name;
    private String    documentNumber;
    private LocalDate birthDate;

    @OneToMany(mappedBy = "consumer", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Contact> contacts;

    @OneToMany(mappedBy = "consumer", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Address> addresses;

    @OneToMany(mappedBy = "consumer", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Card> cards;

    public void merge(final Consumer consumer) {
        this.birthDate      = consumer.birthDate;
        this.documentNumber = consumer.documentNumber;
        this.name           = consumer.name;
    }

}
