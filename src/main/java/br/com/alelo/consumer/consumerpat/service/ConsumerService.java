package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.ConsumerOrCardException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ConsumerService {

    @Autowired
    ConsumerRepository consumerRepository;

    public List<Consumer> listAllConsumers(){
        return consumerRepository.findAll();
    }

    public Consumer createConsumer(Consumer consumer) throws ConsumerOrCardException {
        Consumer newConsumer = consumerRepository.getById(consumer.getId());

        if (null != newConsumer) {
            throw new ConsumerOrCardException("Consumidor já existe");
        }
        if (null == newConsumer) {
            Consumer foodConsumer = consumerRepository.findCardNumberAllTypes(consumer.getFoodCardNumber());
            if (null != foodConsumer) {
                throw new ConsumerOrCardException ("Este cartão de Alimentção já existe por favor utilize outro número");
            } else {
                Consumer drugConsumer = consumerRepository.findCardNumberAllTypes(consumer.getDrugstoreCardNumber());
                if (null != drugConsumer) {
                    throw new ConsumerOrCardException("Este cartão Farmacia já existe por favor utilize outro número");
                } else {
                    Consumer fuelConsumer = consumerRepository.findCardNumberAllTypes(consumer.getFuelCardNumber());
                    if (null != fuelConsumer) {
                        throw new ConsumerOrCardException ("Este cartão de Combustivel já existe por favor utilize outro numero");
                    }
                }
            }
        }
        return consumerRepository.save(consumer);
    }

    public Consumer updateConsumer(Consumer consumerUpdate) throws ConsumerOrCardException{
        Consumer consumer = consumerRepository.getById(consumerUpdate.getId());

        if (null == consumer) {
            throw new ConsumerOrCardException ("Consumidor Invalido");
        }

        if (consumerUpdate.getFoodCardBalance() != consumerUpdate.getFuelCardBalance()) {
            throw new ConsumerOrCardException ("Saldo do cartão Alimentação não pode ser alterado");
        }
        if (consumerUpdate.getDrugstoreCardBalance() != consumerUpdate.getDrugstoreCardBalance()) {
            throw new ConsumerOrCardException ("Saldo do cartão Farmácia não pode ser alterado");
        }
        if (consumerUpdate.getFuelCardNumber() != consumerUpdate.getFuelCardNumber()) {
            throw new ConsumerOrCardException ("Saldo do cartão Combustível não pode ser alterado");
        }
        return consumerRepository.save(consumerUpdate);
    }


}
