package br.com.alelo.consumerpat.core.dto.v1.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@Getter
public class ConsumerCreateV1RequestDto {

    private String name;
    private String document;
    private LocalDate birthDate;
    private AddressV1RequestDto address;
    private Set<CardV1RequestDto> cards;
    private ContactV1RequestDto contact;
}
