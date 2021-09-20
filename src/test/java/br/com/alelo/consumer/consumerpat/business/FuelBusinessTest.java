package br.com.alelo.consumer.consumerpat.business;

import br.com.alelo.consumer.consumerpat.business.impl.FuelBusinessImpl;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.model.dto.EstablishmentTypeEnum;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.utils.MockUtil;
import br.com.alelo.consumer.consumerpat.utils.exception.ProcessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
class FuelBusinessTest {

    @Mock
    protected CardRepository cardRepository;

    @InjectMocks
    protected FuelBusinessImpl fuelBusiness;

    @Test
    void saveCard() {
        var consumer = MockUtil.getConsumer();
        consumer.setFuelCardNumber(12345l);
        consumer.setFuelCardBalance(1.99);
        Mockito.when(cardRepository.findByCardTypeAndCardNumber(ArgumentMatchers.any(Integer.class), ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(MockUtil.getCard(EstablishmentTypeEnum.FUEL, 2.00)));
        Mockito.when(cardRepository.save(ArgumentMatchers.any(Card.class))).thenReturn(MockUtil.getCard(EstablishmentTypeEnum.FUEL, 200.00));
        fuelBusiness.saveCard(consumer);
        Mockito.verify(cardRepository, Mockito.times(1)).save(ArgumentMatchers.any(Card.class));
    }

    @Test
    void setCard()  {
       var consumer = MockUtil.getConsumer();
       fuelBusiness.setCard(consumer, MockUtil.getCard(EstablishmentTypeEnum.FUEL, 200.00));
       Assertions.assertEquals(consumer.getFuelCardBalance(), 200.00);
    }

    @Test
    void existNumberFalse () {
        var consumer = MockUtil.getConsumer();
        consumer.setFuelCardNumber(0);
        Assertions.assertFalse(fuelBusiness.existsNumber(consumer));
    }

    @Test
    void value() throws ProcessException {
        var dtoBuy = MockUtil.getDtoBuy();
        Assertions.assertEquals(fuelBusiness.value(dtoBuy), 13.50);
    }
}
