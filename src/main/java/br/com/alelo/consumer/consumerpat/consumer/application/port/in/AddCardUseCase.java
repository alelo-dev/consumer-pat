package br.com.alelo.consumer.consumerpat.consumer.application.port.in;

import br.com.alelo.consumer.consumerpat.common.application.port.in.CardConverter;
import br.com.alelo.consumer.consumerpat.consumer.application.port.out.LoadConsumerPort;
import br.com.alelo.consumer.consumerpat.common.domain.ConsumerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddCardUseCase {

    private final LoadConsumerPort loadConsumerPort;
    private final CardConverter cardConverter;

    @Transactional
    public void addCard(AddCardCommand command) {
        loadConsumerPort.findByConsumerId(command.getConsumerId())
                .ifPresentOrElse(consumer -> {
                    var card = cardConverter.toEntity(command.getNewCard());
                    card.setHolder(consumer);
                    consumer.addCard(card);
                }, () -> {
                    throw new ConsumerNotFoundException("Consumer not found, consumerId = " + command.getConsumerId());
                });
    }
}
