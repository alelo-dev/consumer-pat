package br.com.alelo.consumer.consumerpat.dto.v1.response;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
public class ContactV1ResponseDto {

    private String mobilePhone;
    private String residencePhone;
    private String phone;
    private String email;
}
