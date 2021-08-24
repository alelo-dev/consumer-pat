package br.com.alelo.consumer.consumerpat.business.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import br.com.alelo.consumer.consumerpat.business.IConsumerBusiness;
import br.com.alelo.consumer.consumerpat.dto.v2.CardDTO;
import br.com.alelo.consumer.consumerpat.dto.v2.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.ConsumerEntity;
import br.com.alelo.consumer.consumerpat.respository.IConsumerEntityRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsumerBusinessImpl implements IConsumerBusiness {

    @Autowired
    IConsumerEntityRepository iConsumerEntityRepository;

    @Override
    public Optional<List<ConsumerDTO>> listAllConsumers() {
        return Optional.ofNullable(iConsumerEntityRepository.findAll()).map(ConsumerDTO::converter);
    }

    @Override
    public Optional<ConsumerDTO> create(ConsumerDTO consumer) {
        return Optional.of(new ConsumerDTO(iConsumerEntityRepository.save(new ConsumerEntity(consumer))));
    }

    @Override
    public Optional<ConsumerDTO> update(ConsumerDTO consumer) {
        
        Optional<ConsumerEntity> entity = iConsumerEntityRepository.findById(consumer.getId());
        
        if (entity.isEmpty()) {
            ConsumerEntity entit = entity.get();
            consumer.setCards(entit.getCards() != null && !entit.getCards().isEmpty()
                    ? entit.getCards().stream().map(CardDTO::new).collect(Collectors.toSet())
                    : null);// workaround para nao atualizar o cartão.
            return Optional.of(new ConsumerDTO(iConsumerEntityRepository.save(new ConsumerEntity(consumer))));
        }

        return Optional.empty();
    }

}
