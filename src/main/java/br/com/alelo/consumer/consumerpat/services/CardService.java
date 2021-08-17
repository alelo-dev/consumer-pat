package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.dto.CardUpdateBalanceDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.CardType;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exceptions.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.exceptions.InvalidBalanceException;
import br.com.alelo.consumer.consumerpat.mapper.ConsumerMapper;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    CardRepository repository;

    @Autowired
    CardService cardService;

    @Autowired
    ExtractRepository extractRepository;

    public Optional<Card> findByCardNumberAndType(Integer cardNumber, CardType cardType) {
        return repository.findByCardNumberAndCardType(cardNumber, cardType);
    }

    public Card getByCardNumberAndType(Integer cardNumber, CardType cardType) {
        return findByCardNumberAndType(cardNumber, cardType).orElseThrow(() -> new CardNotFoundException());
    }

    public Card updateCardBalance(CardUpdateBalanceDTO cardUpdateBalanceDTO) {
        Card card = this.getByCardNumberAndType(cardUpdateBalanceDTO.getCardNumber(), cardUpdateBalanceDTO.getCardType());
        card.setCardBalance(card.getCardBalance() + cardUpdateBalanceDTO.getCardBalance());
        return repository.save(card);
    }

    public void buy(BuyDTO buy) {
        Card card = cardService.getByCardNumberAndType(buy.getCardNumber(), buy.getCardType());
        Double calculatedValue = card.getCardType().getRuleCard().calcular(buy.getValue());
        if (card.getCardBalance().compareTo(calculatedValue) < 1 && card.getCardBalance().compareTo(calculatedValue) != 0) {
            throw new InvalidBalanceException();
        }
        card.setCardBalance(card.getCardBalance() - calculatedValue);
        cardService.updateCardBalance(ConsumerMapper.INSTANCE.cardEntityToDto(card));
        Extract extract = Extract.builder().cardType(buy.getCardType())
                .productDescription(buy.getProductDescription())
                .dateBuy(new Date())
                .cardNumber(buy.getCardNumber())
                .value(buy.getValue())
                .build();
        extractRepository.save(extract);
    }
}
