package br.com.alelo.consumer.consumerpat.mapping;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;

import java.util.ArrayList;
import java.util.List;

public class CardMapping {

    private CardMapping(){}

    public static List<Card> to(ConsumerDTO dto) {
        var cards = new ArrayList<Card>();
        if(dto.getFuelCardNumber() != null) {
            cards.add(Card
                    .builder()
                    .cardBalance(dto.getFuelCardBalance())
                    .cardNumber(dto.getFuelCardNumber())
                    .companyType(dto.getType())
                    .build());
        }
        if(dto.getDrugstoreCardBalance() != null) {
            cards.add(Card
                    .builder()
                    .cardBalance(dto.getDrugstoreCardBalance())
                    .cardNumber(dto.getDrugstoreCardNumber())
                    .companyType(dto.getType())
                    .build());
        }
        if(dto.getFoodCardBalance() != null) {
            cards.add(Card
                    .builder()
                    .cardBalance(dto.getFoodCardBalance())
                    .cardNumber(dto.getFoodCardNumber())
                    .companyType(dto.getType())
                    .build());
        }
        return cards;
    }

}
