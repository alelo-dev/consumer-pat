package br.com.alelo.consumer.consumerpat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private String number;
    private String city;
    private String country;
    private String postalCode;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;

    /**
     * Nao foi utilizado a anotacao @Data do lombok pois seu Hashcode and Equals
     * torna a API menos performatica, uma vez que fará a comparacao de todos os
     * atributos da classe, e em classes com muitos atributos isso se torna um
     * problema de performance
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
