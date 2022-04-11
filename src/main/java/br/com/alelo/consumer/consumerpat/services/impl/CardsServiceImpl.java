package br.com.alelo.consumer.consumerpat.services.impl;

import br.com.alelo.consumer.consumerpat.dtos.CardsDto;
import br.com.alelo.consumer.consumerpat.models.Cards;
import br.com.alelo.consumer.consumerpat.models.enums.EstablishmentEnum;
import br.com.alelo.consumer.consumerpat.repositories.CardsRepository;
import br.com.alelo.consumer.consumerpat.services.CardsService;
import br.com.alelo.consumer.consumerpat.services.mapper.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CardsServiceImpl implements CardsService {

    @Autowired
    private EntityMapper<Cards, CardsDto> cardsDtoEntityMapper;

    @Autowired
    private CardsRepository cardsRepository;

    @Override
    public CardsDto setBalance(EstablishmentEnum establishmentEnum, int cardNumber, double value) {

        Cards cards = null;

        // é cartão de farmácia
        if (establishmentEnum.equals(EstablishmentEnum.DRUGSTORE)) {
            cards = cardsRepository.findByDrugstoreNumber(cardNumber);
            cards.setDrugstoreCardBalance(cards.getDrugstoreCardBalance() + value);
        }

        // é cartão de refeição
        if (establishmentEnum.equals(EstablishmentEnum.FOOD)) {
            cards = cardsRepository.findByFoodCardNumber(cardNumber);
            cards.setFoodCardBalance(cards.getFoodCardBalance() + value);
        }

        // É cartão de combustivel
        if (establishmentEnum.equals(EstablishmentEnum.FUEL)) {
            cards = cardsRepository.findByFuelCardNumber(cardNumber);
            cards.setFuelCardBalance(cards.getFuelCardBalance() + value);
        }

         cardsRepository.save(cards);
        return cardsDtoEntityMapper.map(cards);
    }
}
