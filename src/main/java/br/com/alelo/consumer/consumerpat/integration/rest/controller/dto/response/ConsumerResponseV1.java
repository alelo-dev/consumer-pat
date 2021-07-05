package br.com.alelo.consumer.consumerpat.integration.rest.controller.dto.response;

import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class ConsumerResponseV1 {

    private String consumerCode;
    private Set<CardResponseV1> cards;

    public static ConsumerResponseV1 transformToResponse(Consumer consumer) {
        Set<CardResponseV1> cardsOut = consumer.getCards().stream().map(card -> CardResponseV1.builder().cardCode(card.getCardCode()).build()).collect(Collectors.toSet());
        return  ConsumerResponseV1.builder().consumerCode(consumer.getConsumerCode()).cards(cardsOut).build();
    }

    public static Page<ConsumerResponseV1> transformToResponse(Page<Consumer> consumers) {
        List<ConsumerResponseV1> consumerResponseV1s = consumers.stream().map(consumer -> ConsumerResponseV1.builder().consumerCode(consumer.getConsumerCode()).cards(consumer.getCards().stream().map(card -> CardResponseV1.builder().cardCode(card.getCardCode()).build()).collect(Collectors.toSet())).build()).collect(Collectors.toList());
        return new PageImpl<>(consumerResponseV1s);
    }
}
