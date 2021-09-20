package br.com.alelo.consumer.consumerpat.business;

import br.com.alelo.consumer.consumerpat.business.impl.FoodCardBusinessImpl;
import br.com.alelo.consumer.consumerpat.utils.MockUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class FoodBusinessTest {

    @InjectMocks
    protected FoodCardBusinessImpl foodCardBusiness;


    @Test
    void existNumberFalse () {
        var consumer = MockUtil.getConsumer();
        consumer.setFoodCardNumber(0);
        Assertions.assertFalse(foodCardBusiness.existsNumber(consumer));
    }
}
