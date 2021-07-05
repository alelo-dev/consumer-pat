package br.com.alelo.consumer.consumerpat.domain.service;

import br.com.alelo.consumer.consumerpat.domain.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.service.exception.ApiException;
import br.com.alelo.consumer.consumerpat.domain.service.exception.Code;
import br.com.alelo.consumer.consumerpat.integration.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.integration.respository.ExtractRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ExtractRepository extractRepository;

    @Transactional(rollbackFor = Throwable.class)
    public Card creditBalance(Long number, BigDecimal value) throws ApiException {
        try {
            Optional<Card> cardOut = cardRepository.findByNumber(number);
            if (cardOut.isEmpty()) {
                throw new ApiException(HttpStatus.NOT_FOUND, Code.INVALID_NOT_FOUND);
            }
            cardOut.get().setBalance(
                    cardOut.get().getType().getCardContext().creditValue(
                            cardOut.get().getBalance(),
                            value));
            return cardRepository.save(cardOut.get());
        } catch (ApiException e) {
            log.warn("m=creditBalance, stage=warn, excption={}", e.getCode().getMessage());
            throw e;
        } catch (Exception e) {
            log.error("m=creditBalance, stage=error, excption={}", e.getMessage());
            throw new ApiException(HttpStatus.BAD_REQUEST, Code.INVALID_EXCEPTION);
        }
    }

}
