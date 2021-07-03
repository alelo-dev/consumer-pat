package br.com.alelo.consumer.consumerpat.integration.rest.controller.dto.response;

import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ConsumerResponseV1 {

    private String consumerCode;

    public static ConsumerResponseV1 transformToResponse(Consumer consumer) {
        return  ConsumerResponseV1.builder().consumerCode(consumer.getConsumerCode()).build();
    }

    public static Page<ConsumerResponseV1> transformToResponse(Page<Consumer> consumers) {
        List<ConsumerResponseV1> consumerResponseV1s = consumers.stream().map(consumer -> ConsumerResponseV1.builder().consumerCode(consumer.getConsumerCode()).build()).collect(Collectors.toList());
        return new PageImpl<>(consumerResponseV1s);
    }
}
