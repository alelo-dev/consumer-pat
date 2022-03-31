package br.com.alelo.consumer.consumerpat.service.impls;

import br.com.alelo.consumer.consumerpat.dto.BalanceDTO;
import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.enums.TypeEnum;
import br.com.alelo.consumer.consumerpat.factory.EstablishmentFactory;
import br.com.alelo.consumer.consumerpat.factory.components.interfaces.EstablishmentComponent;
import br.com.alelo.consumer.consumerpat.parser.interfaces.CardBalanceParser;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.service.interfaces.CardService;
import br.com.alelo.consumer.consumerpat.service.interfaces.ExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ExtractService extractService;

    @Autowired
    private CardBalanceParser cardBalanceParser;

    @Override
    public BalanceDTO setBalance(BalanceDTO balance) {
        Card card = cardRepository.findById(balance.getCardNumber())
                .orElseThrow(() -> new IllegalArgumentException("Card not found."));

        card.setBalance(card.getBalance() + balance.getValue());

        return cardBalanceParser.parse(cardRepository.save(card));
    }

    @Override
    public BalanceDTO buy(BuyDTO buy) {
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
         *
         * Tipos de estabelcimentos
         * 1 - Alimentação (food)
         * 2 - Farmácia (DrugStore)
         * 3 - Posto de combustivel (Fuel)
         */

        EstablishmentComponent establishmentComponent =
                EstablishmentFactory.getInstance(TypeEnum.valueOf(buy.getEstablishment().getType().getId()));
        Card card = establishmentComponent.debit(buy);

        extractService.insert(buy);

        return cardBalanceParser.parse(card);
    }
}
