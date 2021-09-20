package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ConsumerService {

    List<Consumer> listAllConsumers();

    void createConsumer(Consumer consumer);

    void updateConsumer(Integer id, Consumer consumer);

    void setBalance(CardDTO cardDTO);

    void buy(BuyDTO buyDTO);

}
