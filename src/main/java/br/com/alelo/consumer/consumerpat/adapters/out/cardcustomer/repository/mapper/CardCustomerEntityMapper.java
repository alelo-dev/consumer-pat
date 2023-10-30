package br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer.repository.mapper;

import br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer.repository.entity.CardCustomerEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.AddressEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.ContactEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.CustomerEntity;
import br.com.alelo.consumer.consumerpat.application.core.domain.cardcustomer.CardCustomer;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Address;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Contact;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CardCustomerEntityMapper {

    public CardCustomerEntity toCardCustomerEntity(CardCustomer cardCustomer) {
        var cardCustomerEntity = new CardCustomerEntity();
        var customerEntity = new CustomerEntity();
        var addressEntity = new AddressEntity();
        var contactEntity = new ContactEntity();

        BeanUtils.copyProperties(cardCustomer, cardCustomerEntity);
        if(Objects.nonNull(cardCustomer.getCustomer()))
            BeanUtils.copyProperties(cardCustomer.getCustomer(), customerEntity);
        if(Objects.nonNull(cardCustomer.getCustomer()) && Objects.nonNull(cardCustomer.getCustomer().getAddress()))
            BeanUtils.copyProperties(cardCustomer.getCustomer().getAddress(), addressEntity);
        if(Objects.nonNull(cardCustomer.getCustomer()) && Objects.nonNull(cardCustomer.getCustomer().getContact()))
            BeanUtils.copyProperties(cardCustomer.getCustomer().getContact(), contactEntity);

        customerEntity.setContact(contactEntity);
        customerEntity.setAddress(addressEntity);
        cardCustomerEntity.setCustomer(customerEntity);

        return cardCustomerEntity;
    }

    public CardCustomer toCardCustomer(CardCustomerEntity cardCustomerEntity) {
        var cardCustomer = new CardCustomer();
        var customer = new Customer();
        var address = new Address();
        var contact = new Contact();

        BeanUtils.copyProperties(cardCustomerEntity, cardCustomer);
        if(Objects.nonNull(cardCustomerEntity.getCustomer()))
            BeanUtils.copyProperties(cardCustomerEntity.getCustomer(), customer);
        if(Objects.nonNull(cardCustomerEntity.getCustomer()) && Objects.nonNull(cardCustomerEntity.getCustomer().getAddress()))
            BeanUtils.copyProperties(cardCustomerEntity.getCustomer().getAddress(), address);
        if(Objects.nonNull(cardCustomerEntity.getCustomer()) && Objects.nonNull(cardCustomerEntity.getCustomer().getContact()))
            BeanUtils.copyProperties(cardCustomerEntity.getCustomer().getContact(), contact);

        customer.setContact(contact);
        customer.setAddress(address);
        cardCustomer.setCustomer(customer);

        return cardCustomer;
    }
}
