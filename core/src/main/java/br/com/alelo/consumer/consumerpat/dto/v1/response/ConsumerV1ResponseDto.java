package br.com.alelo.consumer.consumerpat.dto.v1.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsumerV1ResponseDto {

    private String consumerCode;
    private String name;
    private String document;
    private LocalDate birthDate;
    private ContactV1ResponseDto contact;
    private AddressV1ResponseDto address;
    private CardV1ResponseDto card;
}
