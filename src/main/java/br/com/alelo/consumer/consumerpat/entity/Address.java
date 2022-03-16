package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.lang.NonNull;

import br.com.alelo.consumer.consumerpat.enums.AddressType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_consumer")
    private Consumer consumer;
    @NonNull
    @Enumerated(EnumType.STRING)
    private AddressType type;
    private String country;
    private String city;
    private Integer portalCode;
    private String street;
    private Integer number;
    private String complement;

}
