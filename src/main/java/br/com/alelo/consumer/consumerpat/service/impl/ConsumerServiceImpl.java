package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.CardType;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.mapper.ConsumerMapper;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

    private final ConsumerRepository consumerRepository;
    private final CardRepository cardRepository;
    private final ConsumerMapper consumerMapper;


    /* Deve listar todos os clientes (cerca de 500) */
    public List<Consumer> listAllConsumers() {
        return consumerRepository.getAllConsumersList();
    }

    /* Cadastrar novos clientes */
    @Transactional
    public void createConsumer(ConsumerDTO dto) {

        //ficou manual a parte do mapper, pois de acordo com readme nao pode usar libs como o modelmapper
        Consumer consumer = consumerMapper.mapDtoToEntity(dto);

        Consumer consumerSaved = consumerRepository.save(consumer);

        dto.getCards().stream().forEach(cardDto -> {
            Card card = Card.builder().consumer(consumerSaved)
                    .number(cardDto.getNumber())
                    .balance(cardDto.getBalance())
                    .CardType(CardType.builder().id(cardDto.getCardTypeId()).build()).build();
            cardRepository.save(card);
        });
    }

    // Não deve ser possível alterar o saldo do cartão
    public void updateConsumer(ConsumerDTO dto) {

        Consumer consumerDb = consumerRepository.getOne(dto.getConsumerId());

        //ficou manual a parte do mapper, pois de acordo com readme nao pode usar libs como o modelmapper
        Consumer consumer = consumerMapper.mapDtoToEntity(dto, consumerDb);

        consumerRepository.save(consumer);
    }
}
