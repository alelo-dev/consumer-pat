package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.CustomerDto;
import br.com.alelo.consumer.consumerpat.entity.Customer;
import br.com.alelo.consumer.consumerpat.mapper.CustomerMapper;
import br.com.alelo.consumer.consumerpat.service.CustomerService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/customer")
@Setter(onMethod_ = {@Autowired})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerController extends CrudController<Customer, CustomerDto, CustomerService, CustomerMapper> {

    @Getter
    CustomerService service;

    @Getter
    CustomerMapper mapper;

}
