package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.entity.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.entity.dto.ErrorDTO;
import br.com.alelo.consumer.consumerpat.entity.dto.TransactionDTO;
import br.com.alelo.consumer.consumerpat.entity.enumeration.CardType;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


@Service
public class ShoppingService {

    private EstablishmenService establishmenService;
    private ExtractService extractService;
    private CardService cardService;

    private final BigDecimal percentualFood = BigDecimal.valueOf(0.1);
    private final BigDecimal percentualFuel = BigDecimal.valueOf(0.35);


    @Autowired
    public ShoppingService(EstablishmenService establishmenService, ExtractService extractService, CardService cardService){
        this.establishmenService = establishmenService;
        this. extractService = extractService;
        this.cardService = cardService;
    }

    @Transactional
    public TransactionDTO buy(BuyDTO buyDTO) throws BusinessException {

        validated(buyDTO);
        var establishment = validEstablishmentTypeBuy(buyDTO);
        var card = getCardByNumber(buyDTO.getCardNumber());
        BigDecimal valueDebit = applyPricingRules(card.getCardType(), buyDTO.getValue());

        Extract extract = Extract.builder()
                .cardNumber(card.getCardNumber())
                .establishmentName(establishment.getEstablishmentName())
                .establishmentNameId(establishment.getId())
                .value(valueDebit)
                .productDescription(buyDTO.getProductDescription())
                .dateBuy(LocalDateTime.now())
                .build();


        return this.buy(extract, card);

    }

    private void validated(BuyDTO buyDTO) throws BusinessException {

        List<ErrorDTO> errorList = new ArrayList<>();

        if (isNull(buyDTO.getCardNumber())) {
            errorList.add(ErrorDTO.builder().message("card number not found").build());

        }

        if (isNull(buyDTO.getEstablishmentId())) {
            errorList.add(ErrorDTO.builder().message("Establishment ID not found").build());

        }

        if (nonNull(buyDTO.getValue()) && buyDTO.getValue().compareTo(BigDecimal.ZERO) > 0) {
            errorList.add(ErrorDTO.builder().message("Invalid Value").build());
        }

        if (!errorList.isEmpty()) {
            throw new BusinessException("Invalid Request to By", errorList);
        }
    }

    private Card getCardByNumber(Integer cardNumber) {
        Card card = cardService.findByCardNumber(cardNumber);
        if (isNull(card)) {
            throw new EntityNotFoundException("card not found");
        }
        return card;
    }


    public TransactionDTO buy(Extract extract, Card card) throws BusinessException {

        cardService.validateCardBalance(card, extract.getValue());
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
                    this.applyFoodpricerules(value);
                break;

            case FUEL:
                    this.applyFuelpricerules(value);
                break;
        }

        return value;
    }

    // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
    private BigDecimal applyFuelpricerules(BigDecimal value) {
        return  value.subtract(value.multiply(percentualFood));
    }

    // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
    // Deixei as implementações diferentes para análise dos deves que vão
    // avaliar este código
    private BigDecimal applyFoodpricerules(BigDecimal value) {
        BigDecimal increase = value.multiply(percentualFuel);
        return  value.add(increase);
    }




    private Establishment validEstablishmentTypeBuy(BuyDTO buyDTO) throws BusinessException {

        var establishment = establishmenService.findById(buyDTO.getEstablishmentId());
        if (Objects.nonNull(establishment)) {

            if (!establishment.getCardTypeAccepted().equals(CardType.getById(buyDTO.getEstablishmentType()))) {
                throw new BusinessException("Tipo de Benefício não aceito ou não cadastrado para o estabelecimento ");
            }
        } else {
            throw new BusinessException("Estabelecimento aceita esta cartão ");
        }

        return establishment;
    }

}
