package br.com.alelo.consumer.consumerpat.entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.Nullable;


@Data
@Entity
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer extends AbstractEntity {

    String name;
    String documentNumber;
    LocalDate birthDate;

    //contacts
    String mobilePhoneNumber;
    String residencePhoneNumber;
    String phoneNumber;
    String email;

    //Address
    String street;
    int number;
    String city;
    String country;
    String portalCode;

    //cards
    @Nullable
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    List<Card> cards;

    @Builder
    public Customer(
            final Long id,
            final String name,
            final String documentNumber,
            final LocalDate birthDate,
            final String mobilePhoneNumber,
            final String residencePhoneNumber,
            final String phoneNumber,
            final String email,
            final String street,
            final int number,
            final String city,
            final String country,
            final String portalCode
    ) {
        super(id);
        this.name = name;
        this.documentNumber = documentNumber;
        this.birthDate = birthDate;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.residencePhoneNumber = residencePhoneNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
        this.portalCode = portalCode;
    }
}
