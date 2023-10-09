package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.domain.enums.CardTypeEnum;
import br.com.alelo.consumer.consumerpat.domain.enums.EstablishmentTypeEnum;
import br.com.alelo.consumer.consumerpat.entity.Cards;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.CardsRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class CardsService {


    private final Logger logger = Logger.getLogger(CardsService.class.getName());
    @Autowired
    private CardsRepository cardsRepository;
    @Autowired
    private Cards cards;
    @Autowired
    private Extract extract;
    @Autowired
    private ExtractRepository extractRepository;
    @Autowired
    private Establishment establishment;

    public void setBalance() {

        if (extract.getEstablishmentType() == EstablishmentTypeEnum.FOOD_ESTABLISHMENT.getEstablishmentType()) {
            cards = cardsRepository.findByFoodCardNumber(CardTypeEnum.FOOD_CARD.getCardType());
            cards.setFoodCardBalance(cards.getFoodCardBalance() + extract.getAmount());
            cardsRepository.save(cards);
        } else if (extract.getEstablishmentType() == EstablishmentTypeEnum.DRUGSTORE.getEstablishmentType()) {
            cards = cardsRepository.findByDrugstoreNumber(CardTypeEnum.DRUGSTORE_CARD.getCardType());
            cards.setDrugstoreCardBalance(cards.getDrugstoreCardBalance() + extract.getAmount());
            cardsRepository.save(cards);
        } else if (extract.getEstablishmentType() == EstablishmentTypeEnum.GAS_STATION.getEstablishmentType()) {
            cards = cardsRepository.findByFuelCardNumber(CardTypeEnum.FUEL_CARD.getCardType());
            cards.setFuelCardBalance(cards.getFuelCardBalance() + extract.getAmount());
            cardsRepository.save(cards);
        }
        else logger.info("Cartão não válido para esse estabelecimento");

        extractRepository.save(extract);
    }

    public void buy() {
        if (extract.getEstablishmentType() == EstablishmentTypeEnum.FOOD_ESTABLISHMENT.getEstablishmentType()) {
            cards = cardsRepository.findByFoodCardNumber(CardTypeEnum.FOOD_CARD.getCardType());
            cards.setFoodCardBalance(cards.getFoodCardBalance() - extract.getAmount());
            cardsRepository.save(cards);
        } else if (extract.getEstablishmentType() == EstablishmentTypeEnum.DRUGSTORE.getEstablishmentType()) {
            cards = cardsRepository.findByDrugstoreNumber(CardTypeEnum.DRUGSTORE_CARD.getCardType());
            cards.setDrugstoreCardBalance(cards.getDrugstoreCardBalance() - extract.getAmount());
            cardsRepository.save(cards);
        } else if (extract.getEstablishmentType() == EstablishmentTypeEnum.GAS_STATION.getEstablishmentType()) {
            cards = cardsRepository.findByFuelCardNumber(CardTypeEnum.FUEL_CARD.getCardType());
            cards.setFuelCardBalance(cards.getFuelCardBalance() - extract.getAmount());
            cardsRepository.save(cards);
        }
        else logger.info("Cartão não é válido para esse estabelecimento");

        cardsRepository.save(cards);
    }
}
