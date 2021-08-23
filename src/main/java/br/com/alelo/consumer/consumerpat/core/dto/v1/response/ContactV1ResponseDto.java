package br.com.alelo.consumer.consumerpat.core.dto.v1.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ContactV1ResponseDto {

    private String mobilePhone;
    private String residencePhone;
    private String email;
}
