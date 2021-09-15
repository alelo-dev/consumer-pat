package br.com.alelo.consumer.consumerpat.mapper;

import br.com.alelo.consumer.consumerpat.dto.response.ConsumerDto;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsumerMapper {

    private final AddressMapper addressMapper;
    private final ContactMapper contactMapper;

    public ConsumerDto toDto(Consumer consumer){
        return ConsumerDto.builder()
                .id(consumer.getId())
                .name(consumer.getName())
                .birthDate(consumer.getBirthDate())
                .documentNumber(consumer.getDocumentNumber())
                .address(addressMapper.toDto(consumer.getAddress()))
                .contact(contactMapper.toDto(consumer.getContact()))
                .build();
    }

    public Consumer toEntity(ConsumerDto consumerDto){
        return Consumer.builder()
                .id(consumerDto.getId())
                .name(consumerDto.getName())
                .birthDate(consumerDto.getBirthDate())
                .documentNumber(consumerDto.getDocumentNumber())
                .address(addressMapper.toEntity(consumerDto.getAddress()))
                .contact(contactMapper.toEntity(consumerDto.getContact()))
                .build();
    }
}
