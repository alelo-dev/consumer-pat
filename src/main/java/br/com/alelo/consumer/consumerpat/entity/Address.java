package br.com.alelo.consumer.consumerpat.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Data
@Entity
public class Address {

    //Address
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")

    Integer id;
    @NotEmpty(message = "Rua não pode ser vazia")
    String street;
    @Positive(message = "Número deve ser maior que zero")
    int number;
    @NotEmpty(message = "Cidade não pode ser vazia")
    String city;
    @NotEmpty(message = "País não pode ser vazio")
    String country;
    @Positive(message = "Código postal deve ser maior que zero")
    int portalCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id", nullable = false)
    @JsonBackReference
    private Consumer consumer;

//    Integer consumerId;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return
                this.number == address.number
                        && this.portalCode == address.portalCode
                        && Objects.equals(this.street, address.street)
                        && Objects.equals(this.city, address.city)
                        && Objects.equals(this.country, address.country)
                        && Objects.equals(this.id, address.id);
    }
}
