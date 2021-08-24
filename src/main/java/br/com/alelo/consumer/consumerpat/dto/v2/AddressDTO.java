package br.com.alelo.consumer.consumerpat.dto.v2;

import br.com.alelo.consumer.consumerpat.entity.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    // Usaria MapperStruct
    public AddressDTO(AddressEntity entity) {
        this.id = entity.getId();
        this.street = entity.getStreet();
        this.number = entity.getNumber();
        this.city = entity.getCity();
        this.country = entity.getCity();
        this.portalCode = entity.getPortalCode();
    }

    private Integer id;
    private String street;
    private Integer number;
    private String city;
    private String country;
    private Integer portalCode;
}
