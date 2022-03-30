package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.CardType;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enums.EstablishmentTypeEnum;
import br.com.alelo.consumer.consumerpat.exception.CardException;
import br.com.alelo.consumer.consumerpat.exception.EstablishmentTypeException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    private final ExtractRepository extractRepository;

    @Value("${fuelcard.tax.percent}")
    private BigDecimal fuelcardTax;

    @Value("${foodcard.discount.percent}")
    private BigDecimal foodcardDiscount;

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @Override
    public void setBalance(Long cardNumber, BigDecimal value) throws CardException {

        Card card = cardRepository.findByNumber(cardNumber).orElseThrow(CardException::notFound);

        card.setBalance(card.getBalance().add(value));
        cardRepository.save(card);
    }

    @Override
    public void buy(Long establishmentType, String establishmentName, Long cardNumber, String productDescription, BigDecimal value) throws CardException, EstablishmentTypeException {

        Card card = cardRepository.findByNumber(cardNumber).orElseThrow(CardException::notFound);

        /* Tipos de estabelcimentos
         * 1 - Alimentação (food)
         * 2 - Farmácia (DrugStore)
         * 3 - Posto de combustivel (Fuel)
         */

        switch (EstablishmentTypeEnum.get(establishmentType.intValue())) {
            case FOOD:
                // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
                value = value.subtract(value.multiply(foodcardDiscount));
                break;
            case FUEL:
                // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
                value = value.add(value.multiply(fuelcardTax));
                break;
        }

        card.setCardType(CardType.builder().id(establishmentType).build());
        card.setBalance(card.getBalance().subtract(value));
        cardRepository.save(card);

        Extract extract = Extract.builder().establishmentName(establishmentName)
                .productDescription(productDescription)
                .dateBuy(LocalDate.now())
                .cardNumber(cardNumber)
                .value(value)
                .build();

        extractRepository.save(extract);
    }
}
