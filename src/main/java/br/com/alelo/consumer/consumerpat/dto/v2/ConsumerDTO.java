package br.com.alelo.consumer.consumerpat.dto.v2;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import br.com.alelo.consumer.consumerpat.entity.ConsumerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerDTO {
    
    //Usaria Mapstruct
    public ConsumerDTO (ConsumerEntity entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.documentNumber = entity.getDocumentNumber();
        this.birthDate = entity.getBirthDate();
        this.contactDTO = new ContactDTO(entity.getContactEntity());
        this.addressDTO = new AddressDTO(entity.getAddressEntity());
        this.cards = entity.getCards() != null
                ? entity.getCards().stream().map(CardDTO::new).collect(Collectors.toSet())
                : null;
    }

    private Integer id;
    private String name;
    private Integer documentNumber; 
    private LocalDate birthDate;
    private ContactDTO contactDTO;
    private AddressDTO addressDTO;
    private Set<CardDTO> cards;

    // Usaria Mapstruct
    public static List<ConsumerDTO> converter(List<ConsumerEntity> entities) {

        if (entities != null && !entities.isEmpty()) {
            return entities.stream().map(ConsumerDTO::new).collect(Collectors.toList());
        }

        return null;
    }

}
