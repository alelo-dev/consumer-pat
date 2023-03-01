package br.com.alelo.consumer.consumerpat.consumer.application.port.in;

import br.com.alelo.consumer.consumerpat.consumer.adapter.out.ConsumerApiModel;
import br.com.alelo.consumer.consumerpat.consumer.application.port.out.LoadConsumerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListConsumersUseCase {

    private final LoadConsumerPort loadConsumerPort;
    private final ConsumerConverter consumerConverter;


    public Page<ConsumerApiModel> listAll(ListConsumersCommand command) {
        return loadConsumerPort.findAll(command.getPageable())
                .map(consumerConverter::toApiModel);
    }
}
