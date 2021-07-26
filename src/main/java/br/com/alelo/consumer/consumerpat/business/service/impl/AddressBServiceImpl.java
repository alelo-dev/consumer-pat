package br.com.alelo.consumer.consumerpat.business.service.impl;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 24/07/2021 | 14:18
 */

import br.com.alelo.consumer.consumerpat.business.service.AbstractBService;
import br.com.alelo.consumer.consumerpat.entity.ConsumerAddress;
import br.com.alelo.consumer.consumerpat.respository.ConsumerAddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AddressBServiceImpl extends AbstractBService<ConsumerAddress> {

    public AddressBServiceImpl(ConsumerAddressRepository consumerAddressRepository) {
        super(consumerAddressRepository, AddressBServiceImpl.class);
    }

}
