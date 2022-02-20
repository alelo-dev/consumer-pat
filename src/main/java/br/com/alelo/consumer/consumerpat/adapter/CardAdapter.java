package br.com.alelo.consumer.consumerpat.adapter;

import br.com.alelo.consumer.consumerpat.entity.card.Card;
import br.com.alelo.consumer.consumerpat.vo.CardVo;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CardAdapter {

    public static CardVo modelToVo(Card card){

        return CardVo.builder().balance(card.getBalance())
                        .number(card.getNumber())
                        .type(card.getType())
                        .consumerId(card.getConsumer().getId()).build();
    }


    public static Card voToModel(CardVo card){

        return Card.builder().balance(card.getBalance())
                .number(card.getNumber())
                .type(card.getType())
                .build();
    }

    public static List<CardVo> modelToVo(List<Card> cards){
        if (CollectionUtils.isEmpty(cards)) {
            return new ArrayList<>();
        }
        return cards.stream().map(CardAdapter::modelToVo).collect(Collectors.toList());
    }


    public static List<Card> voToModel(List<CardVo> cards){
        if (CollectionUtils.isEmpty(cards)) {
            return new ArrayList<>();
        }
        return cards.stream().map(CardAdapter::voToModel).collect(Collectors.toList());
    }

}
