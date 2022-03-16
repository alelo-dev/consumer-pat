package br.com.alelo.consumer.consumerpat.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.BalanceDto;
import br.com.alelo.consumer.consumerpat.dto.SaleDto;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enums.CardType;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;

@Service
public class CardService {
    
    private final CardRepository repository;
    private final ExtratcService extratcService;

    @Autowired
    public CardService(CardRepository repository,
        ExtratcService extratcService) {
        this.repository = repository;
        this.extratcService = extratcService;
    }
    
    public List<Card> findAllCards(String consumerName) {
        return this.repository.findByConsumerName(consumerName);
    }

    public Card createCard(Card card) {
        return this.repository.save(card);
    }

    public Card updateCard(Card card, Integer cardId) {
        final var updateCard = this.repository.findById(cardId)
            .map(entity -> {
                entity.setType(entity.getType());
                entity.setNumber(entity.getNumber());
                entity.setBalance(BigDecimal.ZERO);

                return this.repository.save(card);
            }).orElseThrow();

        return updateCard;
    }

    public Card processBuy(SaleDto saleDto) {
        Card card = null;
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
        *
        * Tipos de estabelcimentos
        * 1 - Alimentação (food)
        * 2 - Farmácia (DrugStore)
        * 3 - Posto de combustivel (Fuel)
        */
        BigDecimal cashback = BigDecimal.ZERO;
        BigDecimal tax = BigDecimal.ZERO;

        if (saleDto.getCardType().equals(CardType.FOOD)) {
            cashback  = (saleDto.getValue().divide(new BigDecimal(100))).multiply(BigDecimal.TEN);
            saleDto.setValue(saleDto.getValue().subtract(cashback));
        } else if (saleDto.getCardType().equals(CardType.FUEL)) {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            tax  = (saleDto.getValue().divide(new BigDecimal(100))).multiply(new BigDecimal(35));
            saleDto.setValue(saleDto.getValue().add(tax));
        }

        card = repository.findByTypeAndNumber(saleDto.getCardType(), saleDto.getCardNumber());
        if (card != null) {
            card.setBalance(card.getBalance().subtract(saleDto.getValue()));
            repository.save(card);
    
            Extract extract = new Extract(saleDto.getEstablishmentName(), saleDto.getProductDescription(), new Date(), card, saleDto.getValue());
            extratcService.createExtract(extract);
        }
        return card;
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    public Card updateBalance(BalanceDto balanceDTO) {
        
        Card card = repository.findByTypeAndNumber(CardType.DRUGSSTORE, balanceDTO.getCardNumber());

        if(card != null) {
            // é cartão de farmácia
            card.setBalance(card.getBalance().add(balanceDTO.getValue()));
            repository.save(card);
        } else {
            card = repository.findByTypeAndNumber(CardType.FOOD, balanceDTO.getCardNumber());
            if(card != null) {
                // é cartão de refeição
                card.setBalance(card.getBalance().add(balanceDTO.getValue()));
                repository.save(card);
            } else {
                // É cartão de combustivel
                card = repository.findByTypeAndNumber(CardType.FUEL, balanceDTO.getCardNumber());
                if(card != null) {
                    card.setBalance(card.getBalance().add(balanceDTO.getValue()));
                    repository.save(card);
                }
            }
        }

        return card;
    }
}
