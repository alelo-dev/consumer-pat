package br.com.alelo.consumer.consumerpat.dto.v2;

import br.com.alelo.consumer.consumerpat.entity.PhoneEntity;
import br.com.alelo.consumer.consumerpat.enuns.PhoneTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneDTO {

    public PhoneDTO (PhoneEntity entity){
        this.id = entity.getId();
        this.number = entity.getNumber();
        this.type = PhoneTypeEnum.getEnum(entity.getType());
    }

    @EqualsAndHashCode.Exclude
    private Integer id;
    private String number;
    private PhoneTypeEnum type;
    
}
