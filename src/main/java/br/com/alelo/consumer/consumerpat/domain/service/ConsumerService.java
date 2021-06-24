package br.com.alelo.consumer.consumerpat.domain.service;

import br.com.alelo.consumer.consumerpat.domain.model.Consumer;
import br.com.alelo.consumer.consumerpat.dto.BuyItemRequestDto;
import br.com.alelo.consumer.consumerpat.dto.ConsumerCreationDto;
import br.com.alelo.consumer.consumerpat.dto.ConsumerUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConsumerService {
    void setBalance(int cardNumber, double value);

    void save(ConsumerCreationDto consumerCreationDto);

    void update(ConsumerUpdateDto consumerUpdateDto);

    Page<Consumer> listAllConsumers(Pageable pageable);

    BuyItemRequestDto buy(BuyItemRequestDto buyItemRequestDto);
}
