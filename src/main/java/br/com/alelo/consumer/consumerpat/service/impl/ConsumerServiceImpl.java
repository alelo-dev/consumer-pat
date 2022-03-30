package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

    private final ConsumerRepository repository;

    /* Deve listar todos os clientes (cerca de 500) */
    public List<Consumer> listAllConsumers() {
        return repository.getAllConsumersList();
    }

    /* Cadastrar novos clientes */
    public void createConsumer(Consumer consumer) {
        repository.save(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    public void updateConsumer(Consumer consumer) {
        repository.save(consumer);
    }
}
