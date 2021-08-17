package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.dto.UpdateConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exceptions.CardExistsException;
import br.com.alelo.consumer.consumerpat.exceptions.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.mapper.ConsumerMapper;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumerService {
    @Autowired
    ConsumerRepository repository;

    @Autowired
    CardService cardService;

    public Consumer saveConsumer(ConsumerDTO consumerDTO) {
        consumerDTO.getCards()
                .forEach(card -> {
                    boolean cardExist = cardService.findByCardNumberAndType(card.getCardNumber(), card.getCardType()).isPresent();
                    if (cardExist) throw new CardExistsException();
                });
        return repository.save(ConsumerMapper.INSTANCE.dtoToConsumer(consumerDTO));
    }

    public Consumer updateConsumer(Long id, UpdateConsumerDTO consumerDTO) {
        Optional<Consumer> consumer = repository.findById(id);
        repository.findById(id).orElseThrow(() -> new CardNotFoundException());
        consumerDTO.setId(consumer.get().getId());
        consumerDTO.setCards(consumer.get().getCards());

        return repository.save(ConsumerMapper.INSTANCE.updateDtoToConsumer(consumerDTO));
    }

    public List<Consumer> getAll() {
        return repository.findAll();
    }

    public Page<Consumer> getConsumers(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
