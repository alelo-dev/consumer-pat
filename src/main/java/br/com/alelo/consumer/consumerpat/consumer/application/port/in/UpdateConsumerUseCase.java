package br.com.alelo.consumer.consumerpat.consumer.application.port.in;

import br.com.alelo.consumer.consumerpat.consumer.adapter.out.ConsumerApiModel;
import br.com.alelo.consumer.consumerpat.consumer.application.port.out.LoadConsumerPort;
import br.com.alelo.consumer.consumerpat.consumer.application.port.out.SaveConsumerPort;
import br.com.alelo.consumer.consumerpat.common.domain.ConsumerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateConsumerUseCase {

    private final LoadConsumerPort loadConsumerPort;
    private final ConsumerConverter consumerConverter;
    private final SaveConsumerPort saveConsumerPort;

    @Transactional
    public ConsumerApiModel updateConsumer(UpdateConsumerCommand command) {

        var existingConsumer = loadConsumerPort.findByConsumerId(command.getConsumerId())
                .map(consumer -> consumerConverter.updateEntity(consumer, command.getUpdateConsumer()))
                .orElseThrow(() -> new ConsumerNotFoundException("Consumer not found, consumerId = " + command.getConsumerId()));

        var updatedConsumer = saveConsumerPort.save(existingConsumer);

        return consumerConverter.toApiModel(updatedConsumer);
    }
}
