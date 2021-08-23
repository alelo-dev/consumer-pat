package br.com.alelo.consumerpat.core.usecase.impl;

import br.com.alelo.consumerpat.core.domain.CardDomain;
import br.com.alelo.consumerpat.core.domain.ConsumerDomain;
import br.com.alelo.consumerpat.core.dto.v1.request.ConsumerCreateV1RequestDto;
import br.com.alelo.consumerpat.core.exception.BadRequestException;
import br.com.alelo.consumerpat.core.exception.CardAlreadyRegisteredException;
import br.com.alelo.consumerpat.core.exception.ConsumerAlreadyRegisteredException;
import br.com.alelo.consumerpat.core.exception.ConsumerEmptyException;
import br.com.alelo.consumerpat.core.mapper.domain.ConsumerDomainMapper;
import br.com.alelo.consumerpat.core.usecase.ConsumerCreateUseCase;
import br.com.alelo.consumerpat.dataprovider.repository.AddressRepository;
import br.com.alelo.consumerpat.dataprovider.repository.CardRepository;
import br.com.alelo.consumerpat.dataprovider.repository.ConsumerRepository;
import br.com.alelo.consumerpat.dataprovider.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConsumerCreateUseCaseImpl implements ConsumerCreateUseCase {

    @Autowired
    private ConsumerRepository consumerDao;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ContactRepository contactDao;

    @Autowired
    private CardRepository cardDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(ConsumerCreateV1RequestDto request) throws BadRequestException {
        ConsumerDomain consumerDomain = ConsumerDomainMapper.convert(request);

        if (consumerDomain == null) {
            throw new ConsumerEmptyException();
        }

        ConsumerDomain consumerDomainFromDB = this.consumerDao.findByDocument(request.getDocument());

        if (consumerDomainFromDB != null) {
            throw new ConsumerAlreadyRegisteredException();
        }

        List<CardDomain> byCardNumberIn = this.cardDao.findByCardNumberIn(consumerDomain.getAllCardNumber());

        if (byCardNumberIn.size() > 0) {
            throw new CardAlreadyRegisteredException();
        }

        consumerDomain.validateRequiredFields();
        consumerDomain.generateConsumerCode();

        consumerDomainFromDB = this.consumerDao.save(consumerDomain);

        consumerDomain.getAddress().setConsumer(consumerDomainFromDB);
        consumerDomain.getContact().setConsumer(consumerDomainFromDB);

        this.addressRepository.save(consumerDomain.getAddress());
        this.contactDao.save(consumerDomain.getContact());

        for (CardDomain card : consumerDomain.getCards()) {
            card.setConsumer(consumerDomainFromDB);
            this.cardDao.save(card);
        }

        return consumerDomain.getConsumerCode();
    }
}
