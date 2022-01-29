package br.com.alelo.consumer.consumerpat.service;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.hasLength;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.entity.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.entity.dto.ErrorResponseBuyDTO;
import br.com.alelo.consumer.consumerpat.entity.dto.TransactionDTO;
import br.com.alelo.consumer.consumerpat.entity.enumeration.CardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;


//@TODO Incluir as validações de valor.
@Service
public class ShoppingService {

    @Autowired
    private EstablishmenService establishmenService;
    private ExtractService extractService;
    private CardService cardService;


    public TransactionDTO buy(BuyDTO buyDTO) throws Exception {


        var establishment = validEstablishmentTypeBuy(buyDTO);
        var card = validateCardBalance(buyDTO.getCardNumber(), buyDTO.getValue());
        BigDecimal value = applyPricingRules(card.getCardType(), buyDTO.getValue());

        Extract extract = Extract.builder()
                .cardNumber(card.getCardNumber())
                .establishmentName(establishment.getEstablishmentName())
                .establishmentNameId(establishment.getId())
                .value(value)
                .productDescription(buyDTO.getProductDescription())
                .dateBuy(LocalDateTime.now())
                .build();

        return this.buy(extract, card);

    }

    @Transactional
    public TransactionDTO buy(Extract extract, Card card) {

        cardService.debtByCard(card, extract.getValue());
        extractService.save(extract);

        return TransactionDTO.builder()
                .cardType(card.getCardType().name())
                .cardNumber(card.getCardNumber())
                .value(extract.getValue())
                .transactionDateTime(extract.getDateBuy())
                .build();
    }

    private BigDecimal applyPricingRules(CardType cardType, BigDecimal value) {

        switch (cardType) {

            case FOOD:
                value = applyFoodpricerules(value);
                break;

            case FUEL:
                value = this.applyFuelpricerules(value);
                break;
        }

        return value;
    }

    private BigDecimal applyFuelpricerules(BigDecimal value) {

        return value;
    }

    private BigDecimal applyFoodpricerules(BigDecimal value) {
        return value;
    }


    private Card validateCardBalance(Integer cardNumber, BigDecimal debitValue) {
        Card card = cardService.findByCardNumber(cardNumber);

        return card;
    }

    private Establishment validEstablishmentTypeBuy(BuyDTO buyDTO) throws Exception {

        var establishment = establishmenService.findById(buyDTO.getEstablishmentId());
        if (Objects.nonNull(establishment)) {

            if (!establishment.getCardTypeAccepted().equals(CardType.getById(buyDTO.getEstablishmentType()))) {
                throw new Exception("Tipo de Benefício não aceito ou não cadastrado para o estabelecimento ");
            }
        } else {
            throw new Exception("Estabelecimento aceita esta cartão ");
        }

        return establishment;
    }

    /*    if (establishmentType == 1) {
        // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
        Double cashback  = (value / 100) * 10;
        value = value - cashback;

        consumer = repository.findByFoodCardNumber(cardNumber);
        consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
        service.save(consumer);

    }else if(establishmentType == 2) {
        consumer = repository.findByDrugstoreNumber(cardNumber);
        consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
        repository.save(consumer);

    } else {
        // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
        Double tax  = (value / 100) * 35;
        value = value + tax;

        consumer = repository.findByFuelCardNumber(cardNumber);
        consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
        service.save(consumer);
    }
                }

*/
}
