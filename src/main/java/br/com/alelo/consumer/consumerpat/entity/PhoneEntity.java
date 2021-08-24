package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.alelo.consumer.consumerpat.dto.v2.PhoneDTO;
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
@Entity
public class PhoneEntity {

    //Usaria Mapstruct
    public PhoneEntity (PhoneDTO dto){
        this.id = dto.getId();
        this.number = dto.getNumber();
        this.type = dto.getType();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private Integer id;
    private String number;
    private PhoneTypeEnum type;
}
