package br.com.alelo.consumerpat.core.dto.v1.request;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ConsumerUpdateV1RequestDto {

    private String name;
    private LocalDate birthDate;
    private AddressV1RequestDto address;
    private ContactV1RequestDto contact;
}
