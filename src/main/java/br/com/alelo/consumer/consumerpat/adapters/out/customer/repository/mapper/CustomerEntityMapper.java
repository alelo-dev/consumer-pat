package br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.mapper;

import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.AddressEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.ContactEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.CustomerEntity;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Address;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Contact;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CustomerEntityMapper {

    public CustomerEntity toCustomerEntity(Customer customer) {
        var customerEntity = new CustomerEntity();
        var addressEntity = new AddressEntity();
        var contactEntity = new ContactEntity();

        BeanUtils.copyProperties(customer, customerEntity);
        BeanUtils.copyProperties(customer.getAddress(), addressEntity);
        BeanUtils.copyProperties(customer.getContact(), contactEntity);

        customerEntity.setAddress(addressEntity);
        customerEntity.setContact(contactEntity);

        return customerEntity;
    }

    public Customer toCustomer(CustomerEntity customerEntity) {
        var customer = new Customer();
        var address = new Address();
        var contact = new Contact();

        BeanUtils.copyProperties(customerEntity, customer);
        BeanUtils.copyProperties(customerEntity.getAddress(), address);
        BeanUtils.copyProperties(customerEntity.getContact(), contact);

        customer.setAddress(address);
        customer.setContact(contact);

        return customer;
    }
}
