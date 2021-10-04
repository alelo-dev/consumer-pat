package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enums.CardType;
import br.com.alelo.consumer.consumerpat.mapper.ConsumerMapper;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Optional;


@Slf4j
public class ConsumerServiceImpl implements ConsumerService {

    private final ConsumerRepository consumerRepository;

    private final CardRepository cardRepository;

    private final ExtractRepository extractRepository;

    private final ConsumerMapper consumerMapper;

    @Autowired
    public ConsumerServiceImpl(final ConsumerRepository consumerRepository, final CardRepository cardRepository,
                               final ExtractRepository extractRepository, final ConsumerMapper consumerMapper) {
        this.consumerRepository = consumerRepository;
        this.cardRepository= cardRepository;
        this.extractRepository = extractRepository;
        this.consumerMapper = consumerMapper;
    }

    @Override
    public ConsumerDTO createConsumer(ConsumerDTO consumerDTO) {
        var consumer = consumerMapper.consumerDTOToConsumer(consumerDTO);

        final Optional<Consumer> optionalConsumer = consumerRepository.findByDocumentNumber(consumerDTO.getDocumentNumber());

        if(!optionalConsumer.isPresent()) {
            log.info("M=createConsumer, I=Cadastrando consumidor");
            consumerRepository.save(consumer);
        } else {
            log.info("M=createConsumer, I=Consumidor ja cadastrado");
        }

        return consumerDTO;
    }

    @Override
    public ConsumerDTO updateConsumer(Long id, ConsumerDTO consumerDTO) {
        if (consumerRepository.findById(id).isPresent()){

            Consumer consumer= consumerRepository.findById(id).get();

            log.info("M=updateConsumer, atualizando dados do consumidor id={}", id);

            Consumer consumerUpdate = consumerMapper.consumerDTOToConsumer(consumerDTO);
            consumerUpdate.setId(consumer.getId());

            consumerRepository.save(consumerUpdate);

            return consumerDTO;
        } else{
            return null;
        }
    }

    @Override
    public void addValue(int cardNumber, double value) {
        if(cardRepository.findByCardNumber(cardNumber).isPresent()) {
            log.info("M=addValue, I=Adicionando o valor V=", value);

            Card card = cardRepository.findByCardNumber(cardNumber).get();
            card.setCardBalance(card.getCardBalance()+value);
        } else {
            log.info("M=addValue, I=Cartao nao encontrado");
        }
    }

    @Override
    public Extract debitValue(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {

        Card card = cardRepository.findByCardNumber(cardNumber).get();

        if(card.getCardType().equals(CardType.FOOD)) {
            Double cashback  = (value / 100) * 10;
            value = value - cashback;

            log.info("M=debitValue, efetuando compra no valor de v={}", value);

            card.setCardBalance(card.getCardBalance()-value);
            cardRepository.save(card);
        } else if(card.getCardType().equals(CardType.DRUGSTORE)) {
            log.info("M=debitValue, efetuando compra no valor de v={}", value);

            card.setCardBalance(card.getCardBalance()-value);
            cardRepository.save(card);
        } else {
            Double tax  = (value / 100) * 35;
            value = value + tax;

            log.info("M=debitValue, efetuando compra no valor de v={}", value);

            card.setCardBalance(card.getCardBalance()-value);
            cardRepository.save(card);
        }

        Extract extract = new Extract();
        extract.setEstablishmentName(establishmentName);
        extract.setProductDescription(establishmentName);
        extract.setDateBuy(new Date());
        extract.setCardNumber(cardNumber);
        extract.setValue(value);

        extractRepository.save(extract);

        return extract;
    }
}