package br.com.alelo.consumer.consumerpat.dto.v1.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
public class AddressV1RequestDto {

    private String street;
    private Integer number;
    private String city;
    private String country;
    private String portalCode;
}
