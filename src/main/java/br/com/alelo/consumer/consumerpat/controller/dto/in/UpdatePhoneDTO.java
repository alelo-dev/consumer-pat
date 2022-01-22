package br.com.alelo.consumer.consumerpat.controller.dto.in;

import br.com.alelo.consumer.consumerpat.enums.PhoneTypeEnum;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePhoneDTO {

    private String number;
    private PhoneTypeEnum phoneType;
}
