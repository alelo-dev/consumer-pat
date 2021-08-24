package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import br.com.alelo.consumer.consumerpat.dto.v2.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AddressEntity {

    // Usaria MapperStruct
    public AddressEntity(AddressDTO dto) {
        this.id = dto.getId();
        this.street = dto.getStreet();
        this.number = dto.getNumber();
        this.city = dto.getCity();
        this.country = dto.getCity();
        this.portalCode = dto.getPortalCode();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String street;
    private Integer number;
    private String city;
    private String country;
    private Integer portalCode;

}
