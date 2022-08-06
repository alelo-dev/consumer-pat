package br.com.alelo.consumer.consumerpat.mapper;

import br.com.alelo.consumer.consumerpat.domain.Consumer;
import br.com.alelo.consumer.consumerpat.request.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.response.ConsumerResponse;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class ConsumerMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Consumer toEntity(ConsumerRequest consumerRequest) {
        return modelMapper.map(consumerRequest, Consumer.class);
    }

    public ConsumerResponse toResponse(Consumer consumer) {
        return modelMapper.map(consumer, ConsumerResponse.class);
    }
}

