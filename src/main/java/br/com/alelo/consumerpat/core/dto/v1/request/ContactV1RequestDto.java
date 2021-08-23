package br.com.alelo.consumerpat.core.dto.v1.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ContactV1RequestDto {

    private String mobilePhone;
    private String residencePhone;
    private String email;
}
