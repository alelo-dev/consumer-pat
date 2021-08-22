package br.com.alelo.consumerpat.core.v1.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ConsumerV1ResponseDto {

    private String consumerCode;
    private String name;
    private String document;
    private LocalDate birthDate;
    private ContactV1ResponseDto contact;
    private AddressV1ResponseDto address;
    private Set<CardV1ResponseDto> card;
}
