package br.com.alelo.consumer.consumerpat.services.impl;

import br.com.alelo.consumer.consumerpat.dtos.ExtractDto;
import br.com.alelo.consumer.consumerpat.models.Cards;
import br.com.alelo.consumer.consumerpat.models.enums.EstablishmentEnum;
import br.com.alelo.consumer.consumerpat.repositories.CardsRepository;
import br.com.alelo.consumer.consumerpat.repositories.ExtractRepository;
import br.com.alelo.consumer.consumerpat.services.ExtractService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ExtractServiceImpl implements ExtractService {

    final CardsRepository cardsRepository;
    final ExtractRepository extractRepository;

    public ExtractServiceImpl(CardsRepository cardsRepository, ExtractRepository extractRepository) {
        this.cardsRepository = cardsRepository;
        this.extractRepository = extractRepository;
    }

    @Override
    public ExtractDto buy(ExtractDto extractDto) {

        if (extractDto.getEstablishmentEnum().equals(EstablishmentEnum.FOOD)) {
            buyFoodCard(extractDto);
        }
        if (extractDto.getEstablishmentEnum().equals(EstablishmentEnum.DRUGSTORE)) {
            buyDrugstoreCard(extractDto);
        }

        if (extractDto.getEstablishmentEnum().equals(EstablishmentEnum.FUEL)) {
            buyFuelCard(extractDto);
        }
        return extractDto;

    }

    // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
    private void buyFoodCard(ExtractDto extractDto) {
        Cards cards = null;
        Double cashback  = (extractDto.getValue() / 100) * 10;
        Double value = extractDto.getValue() - cashback;
        cards = cardsRepository.findByFoodCardNumber(extractDto.getCardNumber());
        cards.setFoodCardBalance(cards.getFoodCardBalance() - value);
        cardsRepository.save(cards);
    }


    private void buyDrugstoreCard(ExtractDto extractDto) {
        Cards cards = null;
        cards = cardsRepository.findByDrugstoreNumber(extractDto.getCardNumber());
        cards.setDrugstoreCardBalance(cards.getDrugstoreCardBalance() - extractDto.getValue());
        cardsRepository.save(cards);
    }

    // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
    private void buyFuelCard(ExtractDto extractDto) {
        Cards cards = null;
        double tax = (extractDto.getValue() / 100) * 35;
        double value = extractDto.getValue() + tax;

        cards = cardsRepository.findByFuelCardNumber(extractDto.getCardNumber());
        cards.setFuelCardBalance(cards.getFuelCardBalance() - value);
        cardsRepository.save(cards);
    }
}
