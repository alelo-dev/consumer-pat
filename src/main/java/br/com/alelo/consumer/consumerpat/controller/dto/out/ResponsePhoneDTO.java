package br.com.alelo.consumer.consumerpat.controller.dto.out;

import br.com.alelo.consumer.consumerpat.enums.PhoneTypeEnum;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePhoneDTO {

    private String number;
    private PhoneTypeEnum phoneType;
}
