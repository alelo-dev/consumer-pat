package br.com.alelo.consumer.consumerpat.adapters.in.controller.cardcustomer.mapper;

import br.com.alelo.consumer.consumerpat.adapters.in.controller.cardcustomer.request.CardCustomerRequest;
import br.com.alelo.consumer.consumerpat.adapters.in.controller.cardcustomer.response.CardCustomerResponse;
import br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.response.AddressResponse;
import br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.response.ContactResponse;
import br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.response.CustomerResponse;
import br.com.alelo.consumer.consumerpat.application.core.domain.cardcustomer.CardCustomer;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CardCustomerMapper {
    public CardCustomer toCardCustomer(CardCustomerRequest cardCustomerRequest) {
        var cardCustomer = new CardCustomer();
        var customer = new Customer();

        BeanUtils.copyProperties(cardCustomerRequest, cardCustomer);
        if(Objects.nonNull(cardCustomerRequest.getCustomer()))
            BeanUtils.copyProperties(cardCustomerRequest.getCustomer(), customer);

        cardCustomer.setCustomer(customer);
        cardCustomer.setCardType(cardCustomerRequest.getCardType().value());

        return cardCustomer;
    }

    public CardCustomerResponse toCardCustomerResponse(CardCustomer cardCustomer) {
        var cardCustomerResponse = new CardCustomerResponse();
        var customerReponse = new CustomerResponse();
        var addressResponse = new AddressResponse();
        var contactResponse = new ContactResponse();

        BeanUtils.copyProperties(cardCustomer, cardCustomerResponse);
        if(Objects.nonNull(cardCustomer.getCustomer()))
            BeanUtils.copyProperties(cardCustomer.getCustomer(), customerReponse);
        if(Objects.nonNull(cardCustomer.getCustomer() ) && Objects.nonNull(cardCustomer.getCustomer().getAddress()))
            BeanUtils.copyProperties(cardCustomer.getCustomer().getAddress(), addressResponse);
        if(Objects.nonNull(cardCustomer.getCustomer() ) && Objects.nonNull(cardCustomer.getCustomer().getContact()))
            BeanUtils.copyProperties(cardCustomer.getCustomer().getContact(), contactResponse);

        customerReponse.setAddress(addressResponse);
        customerReponse.setContact(contactResponse);
        cardCustomerResponse.setCustomer(customerReponse);

        return cardCustomerResponse;
    }
}
