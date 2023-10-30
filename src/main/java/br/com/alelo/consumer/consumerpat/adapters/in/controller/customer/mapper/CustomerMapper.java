package br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.mapper;

import br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.request.CustomerRequest;
import br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.response.AddressResponse;
import br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.response.ContactResponse;
import br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.response.CustomerResponse;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Address;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Contact;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest customerRequest) {
        var customer = new Customer();
        var address = new Address();
        var contact = new Contact();

        BeanUtils.copyProperties(customerRequest, customer);
        if(Objects.nonNull(customerRequest.getAddress()))
            BeanUtils.copyProperties(customerRequest.getAddress(), address);
        if(Objects.nonNull(customerRequest.getContact()))
            BeanUtils.copyProperties(customerRequest.getContact(), contact);

        customer.setAddress(address);
        customer.setContact(contact);

        return customer;
    }

    public CustomerResponse toCustomerResponse(Customer customer) {
        var customerReponse = new CustomerResponse();
        var addressResponse = new AddressResponse();
        var contactResponse = new ContactResponse();

        BeanUtils.copyProperties(customer, customerReponse);
        if(Objects.nonNull(customer.getAddress()))
            BeanUtils.copyProperties(customer.getAddress(), addressResponse);
        if(Objects.nonNull(customer.getContact()))
            BeanUtils.copyProperties(customer.getContact(), contactResponse);

        customerReponse.setAddress(addressResponse);
        customerReponse.setContact(contactResponse);

        return customerReponse;
    }
}
