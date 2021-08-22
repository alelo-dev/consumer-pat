package br.com.alelo.consumerpat.core.dto.v1.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
public class ContactV1RequestDto {

    private String mobilePhone;
    private String residencePhone;
    private String email;
}
