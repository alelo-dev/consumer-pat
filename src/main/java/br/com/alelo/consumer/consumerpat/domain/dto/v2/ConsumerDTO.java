package br.com.alelo.consumer.consumerpat.domain.dto.v2;

import br.com.alelo.consumer.consumerpat.domain.entity.ConsumerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerDTO {

    private Integer id;
    private String name;
    private Integer documentNumber;
    private LocalDate birthDate;
    private ContactDTO contactDTO;
    private AddressDTO addressDTO;
    private Set<CardDTO> cards;


    public static ConsumerDTO convertEntityToDto(ConsumerEntity entity){
        if(entity != null){

            return new ConsumerDTO(
                    entity.getId(),
                    entity.getName(),
                    entity.getDocumentNumber(),
                    entity.getBirthDate(),
                    ContactDTO.convertEntityToDto(entity.getContactEntity()),
                    AddressDTO.convertEntityToDto(entity.getAddressEntity()),
                    CardDTO.convertEntityToDto(entity.getCards())
            );
        }
        return null;
    }

    public static List<ConsumerDTO> converterListEntityListDto(List<ConsumerEntity> consumers){
        List<ConsumerDTO> consumersDTO = new ArrayList<>();
        if(consumers!= null && !consumers.isEmpty()){
            consumers.forEach(c -> {
                var cd = ConsumerDTO.convertEntityToDto(c);
                consumersDTO.add(cd);
            });
            return consumersDTO;
        }
        return null;
    }
}
