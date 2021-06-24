package br.com.alelo.consumer.consumerpat.application;

import br.com.alelo.consumer.consumerpat.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConsumerApplicationService {

    Page<ConsumerResponseDto> listAllConsumers(Pageable pageable);
    void createConsumer(ConsumerCreationDto consumer);
    void updateConsumer(ConsumerUpdateDto consumer);
    void setBalance(SetBalanceRequestDto balance);
    void buy(BuyItemRequestDto buyItemRequestDto);

}
