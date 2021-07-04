package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.controller.request.CardRequest;
import br.com.alelo.consumer.consumerpat.controller.request.EstablishmentRequest;
import br.com.alelo.consumer.consumerpat.controller.response.CardResponse;
import br.com.alelo.consumer.consumerpat.domain.card.Card;
import br.com.alelo.consumer.consumerpat.domain.card.CardType;
import br.com.alelo.consumer.consumerpat.domain.extract.Extract;
import br.com.alelo.consumer.consumerpat.infra.handler.exception.badRequest.BalanceBadRequestException;
import br.com.alelo.consumer.consumerpat.infra.handler.exception.notFound.NotFoundException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final ExtractRepository extractRepository;

    public List<Card> save(final List<CardRequest> request, final long consumerId){
        List<Card> list = new ArrayList<>();
        request.stream()
                .map(card -> new Card(card.getCardType(), card.getCardNumber(), card.getBalance(), consumerId))
                .forEach(cardRepo ->{
                    final Card cardSaved = cardRepository.save(cardRepo);
                    list.add(cardSaved);
                } );
        return list;
    }

    public List<CardResponse> findByConsumer(final long consumerId){
        final List<Card> cards = cardRepository.findByConsumerId(consumerId);
        if (cards.isEmpty()){
            throw new NotFoundException("Cards não encontrados");
        }
        return cards
                .stream()
                .map(card -> CardResponse.builder()
                        .cardId(card.getId())
                        .consumerId(consumerId)
                        .balance(card.getBalance())
                        .numberCard(card.getNumberCard())
                        .type(card.getType())
                        .build())
                .collect(Collectors.toList());
    }

    public void updateBalance(long consumerId, CardRequest cardRequest) {
        validate(cardRequest.getBalance());
        cardRepository
                .findByConsumerAndNumberCard(consumerId, cardRequest.getCardNumber())
                .ifPresentOrElse(card -> updateSumBalance(card, cardRequest.getBalance()),
                        ()-> log.info("Card não encontrado"));
    }



    public void registerPurchase(final long consumerId, final EstablishmentRequest request) {
        final CardRequest cardRequest = request.getCard();
        final CardType cardType = cardRequest.getCardType();
        final double balanceDiscounted= cardType.calculate(request.getValue());
        validate(balanceDiscounted);
        cardRepository
                .findByConsumerAndNumberCard(consumerId, cardRequest.getCardNumber())
                .ifPresentOrElse(card -> subtractBalance(card, balanceDiscounted, request),
                        ()-> log.info("Card não encontrado"));
    }

    private void updateSumBalance(Card card, double balance) {

        card.updateBalance(balance);
        cardRepository.save(card);
        // atualiza saldo

    }
    private void subtractBalance(Card card, double balance, EstablishmentRequest request) {

        card.updateBalance(-balance);
        cardRepository.save(card);
        final var extract = new Extract(request.getEstablishmentName(), request.getProductDescription(), card.getNumberCard(), balance);
        extractRepository.save(extract);
    }

    private void validate(final double balance) {
        if (balance < 0){
            throw new BalanceBadRequestException("Valor de atualização de saldo não pode ser menor que ZERO.");
        }
    }
}
