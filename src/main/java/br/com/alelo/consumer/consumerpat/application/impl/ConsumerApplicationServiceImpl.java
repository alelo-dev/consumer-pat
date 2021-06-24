package br.com.alelo.consumer.consumerpat.application.impl;

import br.com.alelo.consumer.consumerpat.application.ConsumerApplicationService;
import br.com.alelo.consumer.consumerpat.domain.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.domain.service.ExtractService;
import br.com.alelo.consumer.consumerpat.dto.*;
import br.com.alelo.consumer.consumerpat.mappers.ConsumerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ConsumerApplicationServiceImpl implements ConsumerApplicationService {

    private final ConsumerService consumerService;
    private final ExtractService extractService;


    public Page<ConsumerResponseDto> listAllConsumers(Pageable pageable){
        return consumerService.listAllConsumers(pageable)
                .map(ConsumerMapper::mapConsumerToConsumerResponseDto);

    }

    public void createConsumer(ConsumerCreationDto consumerCreationDto) {
        consumerService.save(consumerCreationDto);
    }

    public void updateConsumer(ConsumerUpdateDto consumer) {

        consumerService.update(consumer);
    }


    public void setBalance(SetBalanceRequestDto balance) {
        consumerService.setBalance(balance.getCardNumber(), balance.getValue());
    }


    public void buy(BuyItemRequestDto buyItemRequestDto) {
        buyItemRequestDto = consumerService.buy(buyItemRequestDto);
        extractService.saveExtract(buyItemRequestDto);
    }
}
