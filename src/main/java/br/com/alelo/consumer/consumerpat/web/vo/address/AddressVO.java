package br.com.alelo.consumer.consumerpat.web.vo.address;

import br.com.alelo.consumer.consumerpat.model.entity.Address;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "id", "street", "number", "city", "country", "postalCode" })
public class AddressVO implements Serializable {

    private Long id;

    private String street;

    private Integer number;

    private String city;

    private String country;

    private Long postalCode;

    public static AddressVO from(Address address) {
        return AddressVO.builder()
            .id(address.getId())
            .street(address.getStreet())
            .number(address.getNumber())
            .city(address.getCity())
            .country(address.getCountry())
            .postalCode(address.getPostalCode())
            .build();
    }
}
