package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.mapping.ConsumerMapping;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ConsumerService {

    private final ConsumerRepository repository;
    private final CardCreditService cardCreditService;

    public List<ConsumerDTO> getAllConsumersList() {
        return repository.findAll()
                .stream()
                .map(ConsumerMapping::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ConsumerDTO save(ConsumerDTO dto) {
        var consumer = ConsumerMapping.to(dto);
        repository.save(consumer);
        consumer.getCards().forEach(card -> {
            card.setConsumer(consumer);
            cardCreditService.save(card);
        });
        return ConsumerMapping.toDto(consumer);
    }
}
