package br.com.alelo.consumerpat.core.usecase.impl;

import br.com.alelo.consumerpat.core.domain.ConsumerDomain;
import br.com.alelo.consumerpat.core.dto.v1.request.ConsumerCreateV1RequestDto;
import br.com.alelo.consumerpat.core.exception.BadRequestException;
import br.com.alelo.consumerpat.core.exception.ConsumerAlreadyRegisteredException;
import br.com.alelo.consumerpat.core.exception.ConsumerEmptyException;
import br.com.alelo.consumerpat.core.exception.RequiredFieldsException;
import br.com.alelo.consumerpat.core.mapper.domain.ConsumerDomainMapper;
import br.com.alelo.consumerpat.core.mapper.entity.ConsumerEntityMapper;
import br.com.alelo.consumerpat.core.usecase.ConsumerCreateUseCase;
import br.com.alelo.consumerpat.dataprovider.dao.AddressDao;
import br.com.alelo.consumerpat.dataprovider.dao.CardDao;
import br.com.alelo.consumerpat.dataprovider.dao.ConsumerDao;
import br.com.alelo.consumerpat.dataprovider.dao.ContactDao;
import br.com.alelo.consumerpat.dataprovider.entity.ConsumerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConsumerCreateUseCaseImpl implements ConsumerCreateUseCase {

    @Autowired
    private ConsumerDao consumerDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private ContactDao contactDao;

    @Autowired
    private CardDao cardDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(ConsumerCreateV1RequestDto request) throws BadRequestException {
        ConsumerDomain consumerDomain = ConsumerDomainMapper.convert(request);

        if (consumerDomain == null) {
            throw new ConsumerEmptyException();
        }

        ConsumerEntity consumerEntity = this.consumerDao.findByDocument(request.getDocument());

        if (consumerEntity != null) {
            throw new ConsumerAlreadyRegisteredException();
        }

        consumerDomain.validateRequiredFields();
        consumerDomain.generateConsumerCode();

        ConsumerEntity consumer = ConsumerEntityMapper.convert(consumerDomain);
        consumer.getAddress().setConsumer(consumer);
        consumer.getContact().setConsumer(consumer);

        this.consumerDao.save(consumer);
        this.addressDao.save(consumer.getAddress());
        this.contactDao.save(consumer.getContact());

        consumer.getCards().forEach(card -> {
            card.setConsumer(consumer);
            this.cardDao.save(card);
        });

        return consumerDomain.getConsumerCode();
    }
}
