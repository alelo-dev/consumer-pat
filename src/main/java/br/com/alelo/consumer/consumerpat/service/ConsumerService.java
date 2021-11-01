package br.com.alelo.consumer.consumerpat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

@Service
public interface ConsumerService {

    public List<Consumer> list();

    public Consumer findById(Integer id);

    public void update(Integer id, Consumer consumer);

    public Consumer create(Consumer consumer);

    public void setBalance(int cardNumber, double value);

    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription,
            double value);

    public void remove(Integer id);

}
