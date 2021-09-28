package br.com.alelo.consumer.consumerpat.domain.entity;

import javax.persistence.*;

import br.com.alelo.consumer.consumerpat.domain.dto.v2.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ADDRESS")
public class AddressEntity {

    public AddressEntity(AddressDTO addressDTO) {
        this.id = addressDTO.getId();
        this.street = addressDTO.getStreet();
        this.number = addressDTO.getNumber();
        this.city = addressDTO.getCity();
        this.country = addressDTO.getCountry();
        this.portalCode = addressDTO.getPortalCode();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String street;
    private int number;
    private String city;
    private String country;
    private int portalCode;
}
