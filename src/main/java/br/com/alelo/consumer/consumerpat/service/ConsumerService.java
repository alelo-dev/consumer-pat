package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.response.ConsumerDto;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.mapper.ConsumerMapper;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final ConsumerRepository consumerRepository;
    private final ConsumerMapper consumerMapper;

    public List<Consumer> getAllConsumersList() {
        return consumerRepository.getAllConsumersList();
    }

    public Consumer save(Consumer consumer) {
        Consumer consumerDb = null;
       try {
           if(Objects.isNull(consumer) || Objects.isNull(consumer.getName())){
               throw new Exception("Consumer have to be a name.");
           }
           consumerDb = consumerRepository.save(consumer);
       }catch (Exception e){
           e.printStackTrace();
       }
        return consumerDb;
    }

    public Consumer update(ConsumerDto consumerDto) {
        Consumer updatedConsumer = consumerMapper.toEntity(consumerDto);
        try {
            Consumer consumerDb = consumerRepository.findByDocumentNumber(consumerDto.getDocumentNumber())
                                                    .orElseThrow(() -> new Exception("Consumer not found for document: " + consumerDto.getDocumentNumber()));

            updatedConsumer = consumerRepository.save(updatedConsumer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updatedConsumer;
    }
}
