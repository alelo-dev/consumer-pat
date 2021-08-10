package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.dto.CardUpdateBalanceDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.CardType;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exceptions.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.mapper.ConsumerMapper;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CardService {
    @Autowired
    CardRepository repository;

    @Autowired
    CardService cardService;

    @Autowired
    ExtractRepository extractRepository;

    public Card getByCardNumberAndType(Integer cardNumber, CardType cardType) {
        return repository.findByCardNumberAndCardType(cardNumber, cardType).orElseThrow(() -> new CardNotFoundException("Card not found."));
    }

    public Card updateCardBalance(CardUpdateBalanceDTO cardUpdateBalanceDTO) {
        Card card = this.getByCardNumberAndType(cardUpdateBalanceDTO.getCardNumber(), cardUpdateBalanceDTO.getCardType());
        card.setCardBalance(card.getCardBalance() + cardUpdateBalanceDTO.getCardBalance());
        return repository.save(card);
    }

    public void buy(BuyDTO buy) {

        Card card = cardService.getByCardNumberAndType(buy.getCardNumber(), CardType.toEnum(buy.getEstablishmentType()));
        Double calculatedValue = card.getCardType().getRuleCard().calcular(buy.getValue());
        card.setCardBalance(calculatedValue);
        cardService.updateCardBalance(ConsumerMapper.INSTANCE.cardEntityToDto(card));
        Extract extract = Extract.builder().establishmentName(buy.getEstablishmentName())
                .productDescription(buy.getProductDescription())
                .dateBuy(new Date())
                .cardNumber(buy.getCardNumber())
                .value(buy.getValue())
                .build();
        extractRepository.save(extract);
    }
}
