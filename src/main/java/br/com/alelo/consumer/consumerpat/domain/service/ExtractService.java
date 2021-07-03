package br.com.alelo.consumer.consumerpat.domain.service;

import br.com.alelo.consumer.consumerpat.domain.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.entity.Extract;
import br.com.alelo.consumer.consumerpat.domain.service.exception.ApiException;
import br.com.alelo.consumer.consumerpat.domain.service.exception.Code;
import br.com.alelo.consumer.consumerpat.integration.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.integration.respository.ExtractRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
public class ExtractService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ExtractRepository extractRepository;

    @Transactional(rollbackFor = Throwable.class)
    public Extract buy(Extract extract) throws ApiException {
        try {
            extract.setExtractCode(UUID.randomUUID().toString());
            Optional<Card> cardOut = cardRepository.findByNumber(extract.getCards().stream().findFirst().get().getNumber());

            if (cardOut.isEmpty()) {
                throw new ApiException(HttpStatus.NOT_FOUND, Code.INVALID_NOT_FOUND);
            }

            cardOut.get().setBalance(cardOut.get().getType().getCardContext().applyCashback(
                    cardOut.get().getBalance(),
                    extract.getValue()));

            if (cardOut.get().getBalance() < 0) {
                throw new ApiException(HttpStatus.BAD_REQUEST, Code.INVALID_REFUND);
            }

            Card card = cardRepository.save(cardOut.get());
            extract.setCards(Set.of(card));

            return extractRepository.save(extract);
        } catch (ApiException e) {
            log.info("m=buy, stage=warn, excption={}", e.getCode().getMessage());
            throw e;
        } catch (Exception e) {
            log.error("m=buy, stage=warn, excption={}", e.getMessage());
            throw new ApiException(HttpStatus.BAD_REQUEST, Code.INVALID_EXCEPTION);
        }
    }

}
