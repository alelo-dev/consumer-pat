package br.com.alelo.consumer.pat.service;

import br.com.alelo.consumer.pat.domain.EstablishmentType;
import br.com.alelo.consumer.pat.entity.Card;
import br.com.alelo.consumer.pat.entity.Extract;
import br.com.alelo.consumer.pat.exception.CardNotFoundException;
import br.com.alelo.consumer.pat.exception.InvalidCardTypeException;
import br.com.alelo.consumer.pat.respository.CardRepository;
import br.com.alelo.consumer.pat.respository.ExtractRepository;
import br.com.alelo.consumer.pat.strategy.ValueChanger;
import br.com.alelo.consumer.pat.strategy.ChangeValueStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final CardRepository cardRepository;
    private final ExtractRepository extractRepository;
    private final ChangeValueStrategy changeValueStrategy;

    @Transactional
    public void buyProduct(final EstablishmentType establishmentType, final Long establishmentId, final String establishmentName,
        final String cardNumber, final String productDescription, final BigDecimal value) {
        Card card = cardRepository.findByCardNumber(cardNumber).orElseThrow(CardNotFoundException::new);

        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
         *
         * Tipos de estabelcimentos
         * 1 - Alimentação (food)
         * 2 - Farmácia (DrugStore)
         * 3 - Posto de combustivel (Fuel)
         */
        if (establishmentType != card.getEstablishmentType()) {
            throw new InvalidCardTypeException("Cartão não permitido neste estabelecimento!");
        }

        final ValueChanger valueChanger = changeValueStrategy.from(establishmentType);
        final BigDecimal newValue = valueChanger.change(value);

        card.setBalance(card.getBalance().subtract(newValue));
        cardRepository.save(card);

        Extract extract = Extract.builder()
            .establishmentId(establishmentId)
            .establishmentName(establishmentName)
            .productDescription(productDescription)
            .buyDate(LocalDateTime.now())
            .cardNumber(cardNumber)
            .value(newValue)
            .build();

        extractRepository.save(extract);
    }

}
