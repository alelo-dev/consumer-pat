package br.com.alelo.consumer.consumerpat.service.api;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

@Component
public interface ConsumerServiceApi {

    public List<Consumer> listAllConsumers();

    public void createConsumer(Consumer consumer);

    public void updateConsumer(Consumer consumer);

    public void setBalance(int cardNumber, double value);

    public void but(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value);

}