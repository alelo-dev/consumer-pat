package br.com.alelo.consumer.consumerpat.dto.v1.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
public class ContactV1RequestDto {

    private String mobilePhone;
    private String residencePhone;
    private String phone;
    private String email;
}
