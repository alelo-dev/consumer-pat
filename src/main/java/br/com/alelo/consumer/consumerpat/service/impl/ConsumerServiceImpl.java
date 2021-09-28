package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.domain.dto.v2.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.AddressEntity;
import br.com.alelo.consumer.consumerpat.domain.entity.ConsumerEntity;
import br.com.alelo.consumer.consumerpat.domain.entity.ContactEntity;
import br.com.alelo.consumer.consumerpat.domain.entity.enums.ErrorMessage;
import br.com.alelo.consumer.consumerpat.domain.exception.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Override
    public ConsumerEntity saveConsumer(ConsumerDTO consumer) {
        return consumerRepository.save(new ConsumerEntity(consumer));
    }

    @Override
    public ConsumerEntity updateConsumer(int idConsumer, ConsumerDTO consumer) throws ConsumerNotFoundException {
        var cons = consumerRepository.findById(idConsumer);

        if(cons.isEmpty())
            throw new ConsumerNotFoundException(ErrorMessage.CONSUMIDOR_NAO_ENCONTRADO);

        //O JPA atualiza o registro em contexto, entao para modificar esse registro devemos sobrescrever o
        // resultado que ele carregou. Isso nos permite garantir que nao haja mudanca e/ou atualizacao no saldo do cartao atraves desse update
        cons.get().setName(consumer.getName());
        cons.get().setDocumentNumber(consumer.getDocumentNumber());
        cons.get().setBirthDate(consumer.getBirthDate());
        cons.get().setContactEntity(new ContactEntity(consumer.getContactDTO()));
        cons.get().setAddressEntity(new AddressEntity(consumer.getAddressDTO()));
        return consumerRepository.save(cons.get());
    }

    @Override
    public List<ConsumerEntity> getAllConsumers() {
        return consumerRepository.findAll();
    }

    @Override
    public ConsumerEntity getConsumerById(int consumerId) throws ConsumerNotFoundException {
        return consumerRepository.findById(consumerId).orElseThrow(() -> new ConsumerNotFoundException(ErrorMessage.CONSUMIDOR_NAO_ENCONTRADO));
    }

    @Override
    public ConsumerEntity getByCardNumber(int cardNumber) {
        return null;
    }

}
