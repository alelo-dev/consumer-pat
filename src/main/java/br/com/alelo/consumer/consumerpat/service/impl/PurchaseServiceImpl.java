package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.domain.Card;
import br.com.alelo.consumer.consumerpat.domain.CardType;
import br.com.alelo.consumer.consumerpat.domain.Extract;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.mapper.PurchaseMapper;
import br.com.alelo.consumer.consumerpat.request.PurchaseRequest;
import br.com.alelo.consumer.consumerpat.response.ExtractResponse;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ExtractRepository extractRepository;

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Override
    public ExtractResponse purchase(PurchaseRequest purchaseRequest) {
        log.info("Buy products with data = {}", purchaseRequest);

        Card card = cardRepository.findByCardNumber(purchaseRequest.getCardNumber())
                .orElseThrow(() -> new BusinessException("Card not found", HttpStatus.UNPROCESSABLE_ENTITY));

        validateCardInactive(card);
        validateLimiteCard(purchaseRequest,card);

        Extract extractBuy = purchaseMapper.toEntity(purchaseRequest, card);

        validateBalanceCard(purchaseRequest, card, extractBuy);

        Extract savedExtract = extractRepository.save(extractBuy);

        return purchaseMapper.toResponse(savedExtract);
    }

    @Override
    public ExtractResponse getByPurchaseCode(String purchaseCode) {
        log.info("Find by purchase code = {}", purchaseCode);

        Extract extract = extractRepository.findByPurchaseCode(purchaseCode)
                .orElseThrow(() -> new BusinessException("Extract not found", HttpStatus.NOT_FOUND));

        return purchaseMapper.toResponse(extract);
    }

    private void validateBalanceCard(PurchaseRequest buyRequest, Card card, Extract extractBuy) {

        switch (buyRequest.getEstablishmentType()) {
            case FOOD_SQUARE:
                if (!card.getCardtype().equals(CardType.FOOD)) {
                    throw new BusinessException("This card is not accepted at this establishment", HttpStatus.UNPROCESSABLE_ENTITY);
                }
                if (card.getCardtype().equals(CardType.FOOD)) {
                    BigDecimal percentFood = buyRequest.getAmount().multiply(new BigDecimal(0.10));
                    card.setCardBalance(card.getCardBalance().subtract(buyRequest.getAmount().subtract(percentFood)));
                    cardRepository.save(card);
                }
                break;

            case PHARMACY:
                if (!card.getCardtype().equals(CardType.DRUG)) {
                    throw new BusinessException("This card is not accepted at this establishment", HttpStatus.UNPROCESSABLE_ENTITY);
                }
                if (card.getCardtype().equals(CardType.DRUG)) {
                    card.setCardBalance(card.getCardBalance().subtract(buyRequest.getAmount()));
                    cardRepository.save(card);
                }
                break;

            case FUEL_STATION:
                if (!card.getCardtype().equals(CardType.FUEL)) {
                    throw new BusinessException("This card is not accepted at this establishment", HttpStatus.UNPROCESSABLE_ENTITY);
                }
                if (card.getCardtype().equals(CardType.FUEL)) {
                    BigDecimal percentFuel = buyRequest.getAmount().multiply(new BigDecimal(0.35));
                    card.setCardBalance(card.getCardBalance().subtract(buyRequest.getAmount().add(percentFuel)));
                    cardRepository.save(card);
                }
                break;
        }
    }

    private void validateLimiteCard(PurchaseRequest purchaseRequest, Card card) {
        if(card.getCardBalance().compareTo(purchaseRequest.getAmount()) < 0){
            throw new BusinessException("No limit for purchase", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    private void validateCardInactive(Card card) {
        if (!card.isActive()) {
            throw new BusinessException("Purchase denied, inactive card", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
