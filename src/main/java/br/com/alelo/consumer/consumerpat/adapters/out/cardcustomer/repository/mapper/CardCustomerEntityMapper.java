package br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer.repository.mapper;

import br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer.repository.entity.CardCustomerEntity;
import br.com.alelo.consumer.consumerpat.application.core.domain.cardcustomer.CardCustomer;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CardCustomerEntityMapper {

    public CardCustomerEntity toCardCustomerEntity(CardCustomer cardCustomer) {
        var cardCustomerEntity = new CardCustomerEntity();

        BeanUtils.copyProperties(cardCustomer, cardCustomerEntity);

        return cardCustomerEntity;
    }

    public CardCustomer toCardCustomer(CardCustomerEntity cardCustomerEntity) {
        var cardCustomer = new CardCustomer();
        var customer = new Customer();

        BeanUtils.copyProperties(cardCustomerEntity, cardCustomer);
        if(Objects.nonNull(cardCustomerEntity.getCustomer()))
            BeanUtils.copyProperties(cardCustomerEntity.getCustomer(), customer);

        cardCustomer.setCustomer(customer);

        return cardCustomer;
    }
}
