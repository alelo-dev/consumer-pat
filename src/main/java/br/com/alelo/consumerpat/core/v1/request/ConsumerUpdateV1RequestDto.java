package br.com.alelo.consumerpat.core.v1.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Getter
public class ConsumerUpdateV1RequestDto {

    private String name;
    private LocalDate birthDate;
    private AddressV1RequestDto address;
    private ContactV1RequestDto contact;
}
