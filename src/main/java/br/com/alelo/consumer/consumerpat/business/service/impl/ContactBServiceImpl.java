package br.com.alelo.consumer.consumerpat.business.service.impl;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 24/07/2021 | 14:18
 */

import br.com.alelo.consumer.consumerpat.business.service.AbstractBService;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.ConsumerContact;
import br.com.alelo.consumer.consumerpat.exception.ConsumerContactCreationException;
import br.com.alelo.consumer.consumerpat.exception.ConsumerRecoverObjectCustomerException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerContactRepository;
import br.com.alelo.consumer.consumerpat.util.Applog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ContactBServiceImpl extends AbstractBService<ConsumerContact> {

    public ContactBServiceImpl(ConsumerContactRepository consumerContactRepository) {
        super(consumerContactRepository, ContactBServiceImpl.class);
    }


}
