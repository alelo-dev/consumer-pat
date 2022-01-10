package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.CardBuyRequest;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.util.exception.BalanceException;
import br.com.alelo.consumer.consumerpat.util.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.util.exception.CardNotTypeException;
import br.com.alelo.consumer.consumerpat.util.exception.EstablishmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExtractService {

    private final CardService cardService;
    private final ExtractRepository extractRepository;
    private final EstablishmentRepository establishmentRepository;
    private final CardRepository cardRepository;
    private final BuyFoodService buyFoodService;
    private final BuyDrugstoreService buyDrugstoreService;
    private final BuyFuelService buyFuelService;
    private BuyService buyService;

    private static final String ALIMENTACAO = "1";
    private static final String FARMACIA = "2";


    public void buy(CardBuyRequest card, String idEstablishment, String productDescription, BigDecimal value) throws Exception{
        if(!verifyIsEstablishment(idEstablishment)) {
            throw new EstablishmentNotFoundException("Establishiment not found");
        }
        if(!cardService.verifyIsCard(card)) {
            throw new CardNotFoundException("Card not found");
        }
        if(!cardService.verifyCardIsType(card)){
            throw new CardNotTypeException("Card Number is not type "+card.getTypeCard());
        }
        if(!cardService.verifyIsBalance(card, value)) {
            throw new BalanceException("No cash balance");
        }
        if(card.getTypeCard().equals("ALIMENTACAO")){
            buyService = buyFoodService;
        }
        if(card.getTypeCard().equals("FARMACIA")){
            buyService = buyDrugstoreService;
        }
        if(card.getTypeCard().equals("COMBUSTIVEL")){
            buyService = buyFuelService;
        }

        buyService.buy(card.getCardNumber(), value);

        saveExtract(card, idEstablishment, productDescription, value);
    }

    private void saveExtract(CardBuyRequest card, String idEstablishment, String productDescription, BigDecimal value) throws Exception{
        Establishment  establishment = establishmentRepository.findById(idEstablishment).get();
        Card cardDB = cardRepository.findByCardNumber(card.getCardNumber()).get();

        extractRepository.save(Extract.builder()
                        .idExtract(UUID.randomUUID().toString())
                        .stablishment(establishment)
                        .card(cardDB)
                        .productDescription(productDescription)
                        .dateBuy(new Date())
                        .value(value)
                .build());
    }

    private boolean verifyIsEstablishment(String idEstablishment) {
        return establishmentRepository.findById(idEstablishment).isPresent();
    }
}
