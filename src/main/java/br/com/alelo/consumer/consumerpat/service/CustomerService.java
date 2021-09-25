package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Customer;
import br.com.alelo.consumer.consumerpat.respository.CustomerRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Setter(onMethod_ = {@Autowired})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerService extends CrudService<Customer, CustomerRepository> {

    @Getter
    CustomerRepository repository;

}
