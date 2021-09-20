package br.com.alelo.consumer.consumerpat.business;

import br.com.alelo.consumer.consumerpat.business.impl.DrugStoreBusinessImpl;
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
class DrugStoreBusinessTest {

    @Mock
    protected CardRepository cardRepository;

    @InjectMocks
    protected DrugStoreBusinessImpl drugStoreBusiness;

    @Test
    void saveCard() {
        var consumer = MockUtil.getConsumer();
        consumer.setDrugstoreNumber(12345l);
        consumer.setDrugstoreCardBalance(1.99);
        Mockito.when(cardRepository.findByCardTypeAndCardNumber(ArgumentMatchers.any(Integer.class), ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(MockUtil.getCard(EstablishmentTypeEnum.DRUG_STONE, 2.00)));
        Mockito.when(cardRepository.save(ArgumentMatchers.any(Card.class))).thenReturn(MockUtil.getCard(EstablishmentTypeEnum.DRUG_STONE, 200.00));
        drugStoreBusiness.saveCard(consumer);
        Mockito.verify(cardRepository, Mockito.times(1)).save(ArgumentMatchers.any(Card.class));
    }

    @Test
    void setCard()  {
       var consumer = MockUtil.getConsumer();
       drugStoreBusiness.setCard(consumer, MockUtil.getCard(EstablishmentTypeEnum.DRUG_STONE, 200.00));
       Assertions.assertEquals(consumer.getDrugstoreCardBalance(), 200.00);
    }

    @Test
    void existNumberFalse () {
        var consumer = MockUtil.getConsumer();
        consumer.setDrugstoreNumber(0);
        Assertions.assertFalse(drugStoreBusiness.existsNumber(consumer));
    }

    @Test
    void existNumberTrue () {
        var consumer = MockUtil.getConsumer();
        consumer.setDrugstoreNumber(1234567890123456l);
        Mockito.when(cardRepository.findByCardTypeAndCardNumber(ArgumentMatchers.any(Integer.class), ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(MockUtil.getCard(EstablishmentTypeEnum.DRUG_STONE, 100.00)));
        Assertions.assertTrue(drugStoreBusiness.existsNumber(consumer));
    }

    @Test
    void value() throws ProcessException {
        var dtoBuy = MockUtil.getDtoBuy();
        Assertions.assertEquals(drugStoreBusiness.value(dtoBuy), 10.00);
    }
}
