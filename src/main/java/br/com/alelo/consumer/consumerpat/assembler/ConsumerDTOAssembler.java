package br.com.alelo.consumer.consumerpat.assembler;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConsumerDTOAssembler {

    public ConsumerDTO toModel(Consumer consumer) {
        ConsumerDTO consumerDTO = new ConsumerDTO();
        consumerDTO.setId(consumer.getId());
        consumerDTO.setName(consumer.getName());
        consumerDTO.setBirthDate(consumer.getBirthDate());
        consumerDTO.setDocumentNumber(consumer.getDocumentNumber());
        consumerDTO.setContacts(consumer.getContacts());
        consumerDTO.setAddress(consumer.getAddress());

        return consumerDTO;

    }

    public List<ConsumerDTO> toCollectionModel(List<Consumer> consumers){
        return consumers.stream()
                .map(consumer -> toModel(consumer))
                .collect(Collectors.toList());

    }
}
