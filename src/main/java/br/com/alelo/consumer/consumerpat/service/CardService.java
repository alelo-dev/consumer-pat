package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.response.CardDto;
import br.com.alelo.consumer.consumerpat.entity.BusinessType;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.utils.CardUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardUtils cardUtils;
    private final CardRepository cardRepository;
    private final ConsumerService consumerService;

    public Card findByNumber(int number) throws Exception {
        Card card = null;
        try {
            card = cardRepository.findByNumber(number)
                                .orElseThrow(() -> new Exception("Card not found for number " + number));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return card;
    }

    //Deve indentificar qual o cartão correto a ser recarregado,
    public Card findByTypeAndNumber(BusinessType type, int number) throws Exception {
        Card card = null;
        try {
            card = cardRepository.findByTypeAndNumber(type.name(), number)
                                .orElseThrow(() -> new Exception("Card not found for number " + number + " and type " + type));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return card;
    }

    //Deve creditar(adicionar) um valor(value) em um no cartão.
    public Card credit(Card card, double value) {
        try {
            card.credit(value);
            cardRepository.save(card);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return card;
    }

    //Deve debitar(subtrair) um valor(value) em um no cartão.
    public void debit(Card card, double value) {
        try {
            switch (card.getType()){
                case FOOD: value = cardUtils.calculateFoodCardValue(value);
                case FUEL: value = cardUtils.calculateFuelCardValue(value);
            }

            card.debit(value);
            cardRepository.save(card);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Não deve ser possível alterar o saldo do cartão
    public Card update(CardDto cardDto) throws Exception {
        Card card = null;
        try {
            card = findByTypeAndNumber(cardDto.getType(), cardDto.getNumber());

            if(card.getBalance() != cardDto.getBalance()) {
                throw new Exception("Card's balance don't be updated.");
            }
            consumerService.update(cardDto.getConsumer());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return card;
    }

}
