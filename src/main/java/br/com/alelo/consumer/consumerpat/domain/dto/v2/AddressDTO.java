package br.com.alelo.consumer.consumerpat.domain.dto.v2;

import br.com.alelo.consumer.consumerpat.domain.entity.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private int id;
    private String street;
    private int number;
    private String city;
    private String country;
    private int portalCode;

    public static AddressDTO convertEntityToDto(AddressEntity addressEntity){
        if(addressEntity != null){
            return new AddressDTO(
                    addressEntity.getId(),
                    addressEntity.getStreet(),
                    addressEntity.getNumber(),
                    addressEntity.getCity(),
                    addressEntity.getCountry(),
                    addressEntity.getPortalCode());
        }
        return null;
    }
}
