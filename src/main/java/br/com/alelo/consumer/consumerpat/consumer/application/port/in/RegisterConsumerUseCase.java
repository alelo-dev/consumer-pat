package br.com.alelo.consumer.consumerpat.consumer.application.port.in;

import br.com.alelo.consumer.consumerpat.consumer.adapter.out.ConsumerApiModel;
import br.com.alelo.consumer.consumerpat.consumer.application.port.out.SaveConsumerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterConsumerUseCase {

    private final SaveConsumerPort saveConsumerPort;
    private final ConsumerConverter consumerConverter;

    @Transactional
    public ConsumerApiModel registerConsumer(RegisterConsumerCommand command) {
        var consumer = consumerConverter.toEntity(command);
        return consumerConverter.toApiModel(saveConsumerPort.save(consumer));
    }
}
