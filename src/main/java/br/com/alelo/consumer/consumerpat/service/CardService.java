package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.model.exception.CustomException;
import br.com.alelo.consumer.consumerpat.model.request.AddBalanceRequest;
import br.com.alelo.consumer.consumerpat.model.request.BuyRequest;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.utils.MaskUtils;
import br.com.alelo.consumer.consumerpat.utils.types.CardAndEstablishmentType;
import br.com.alelo.consumer.consumerpat.validator.CardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.com.alelo.consumer.consumerpat.utils.types.ExceptionsType.*;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    MessageService messageService;

    @Autowired
    ConsumerService consumerService;

    @Autowired
    EstablishmentService establishmentService;

    @Autowired
    ExtractService extractService;

    @Autowired
    CardValidator validator;

    public void saveAll(final List<Card> cards) {
        cards.forEach(this::adjustCard);

        cardRepository.saveAll(cards);
    }

    public void adjustCard(final Card card) {
        validator.accept(card);
        card.setNumber(MaskUtils.removeCardNumberMask(card.getNumber()));
    }

    public void addBalance(final AddBalanceRequest addBalanceRequest) {

        if (addBalanceRequest.getValue() < 0) {
            throw new CustomException(messageService.get(CARD_ILLEGAL_BALANCE_UPDATE.getMessage()),
                    HttpStatus.BAD_REQUEST, CARD_ILLEGAL_BALANCE_UPDATE.getCode());
        }

        cardRepository.findByCardNumber(addBalanceRequest.getCardNumber()).ifPresent(persistedCard -> {

            //garantia extra que está sendo adicionado valor pro cartão do consumidor desejado
            checkIfCardBelongsToConsumer(addBalanceRequest.getConsumerDocumentNumber(), persistedCard);

            persistedCard.setBalance(persistedCard.getBalance() + addBalanceRequest.getValue());
            cardRepository.save(persistedCard);
        });
    }

    private void checkIfCardBelongsToConsumer(final String consumerDocumentNumber, final Card persistedCard) {
        final Consumer persistedConsumer = consumerService.findConsumerByDocumentNumber(consumerDocumentNumber);

        if (isEmpty(persistedConsumer.getCards()) || persistedConsumer.getCards().stream()
                .noneMatch(card -> card.getNumber().equals(persistedCard.getNumber()))) {
            throw new CustomException(messageService.get(CARD_NOT_FROM_CONSUMER.getMessage()), HttpStatus.BAD_REQUEST,
                    CARD_NOT_FROM_CONSUMER.getCode());
        }
    }

    public void buySomething(BuyRequest buyRequest) {

        final Establishment persistedEstablishment = establishmentService.getOrCreate(buyRequest.getEstablishment());

        final Optional<Card> checkPersistedCard = cardRepository.findByCardNumber(buyRequest.getCardNumber());
        if (checkPersistedCard.isEmpty()) {
            throw new CustomException(messageService.get(CARD_NOT_FOUND.getMessage(), buyRequest.getCardNumber()),
                    HttpStatus.BAD_REQUEST, CARD_NOT_FOUND.getCode());
        }

        final Card persistedCard = checkPersistedCard.get();
        checkIfCardBelongsToConsumer(buyRequest.getConsumerDocumentNumber(), persistedCard);

        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da
        compra.
         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do
         cartão e alimentação
         *
         * Tipos de estabelcimentos
         * 1 - Alimentação (food)
         * 2 - Farmácia (DrugStore)
         * 3 - Posto de combustivel (Fuel)
         */
        double finalValue = buyRequest.getValue();
        checkIfPurchaseIsPossible(persistedCard, buyRequest.getEstablishment().getType());
        switch (buyRequest.getEstablishment().getType()) {
            case DRUGSTORE:
                //após estruturação, não há mais regra pra esse caso
                break;
            case FOOD:
                finalValue *= 0.9;
                break;
            case FUEL:
                finalValue *= 1.35;
                break;
        }

        persistedCard.setBalance(persistedCard.getBalance() - finalValue);

        if (persistedCard.getBalance() < 0) {
            throw new CustomException(messageService.get(PURCHASE_BLOCKED_BALANCE.getMessage()), HttpStatus.BAD_REQUEST,
                    PURCHASE_BLOCKED_BALANCE.getCode());
        }

        cardRepository.save(persistedCard);

        extractService.createExtract(buyRequest, persistedEstablishment, persistedCard);
    }

    private void checkIfPurchaseIsPossible(final Card persistedCard, final CardAndEstablishmentType type) {
        if (persistedCard.isDiscontinued()) {
            throw new CustomException(messageService.get(PURCHASE_BLOCKED_DISCONTINUED.getMessage()),
                    HttpStatus.BAD_REQUEST, PURCHASE_BLOCKED_DISCONTINUED.getCode());
        } else if (persistedCard.getType() != type) {
            throw new CustomException(messageService.get(PURCHASE_BLOCKED_TYPE.getMessage(),
                    persistedCard.getType().name(), type.name()), HttpStatus.BAD_REQUEST,
                    PURCHASE_BLOCKED_TYPE.getCode());
        }
    }

}
