package br.com.alelo.consumerpat.core.usecase.impl;

import br.com.alelo.consumerpat.core.domain.ConsumerDomain;
import br.com.alelo.consumerpat.core.dto.v1.request.ConsumerUpdateV1RequestDto;
import br.com.alelo.consumerpat.core.exception.ConsumerNotFound;
import br.com.alelo.consumerpat.core.mapper.domain.ConsumerDomainMapper;
import br.com.alelo.consumerpat.core.usecase.ConsumerUpdateUseCase;
import br.com.alelo.consumerpat.dataprovider.repository.AddressRepository;
import br.com.alelo.consumerpat.dataprovider.repository.ConsumerRepository;
import br.com.alelo.consumerpat.dataprovider.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConsumerUpdateUseCaseImpl implements ConsumerUpdateUseCase {

    @Autowired
    private ConsumerRepository consumerDao;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ContactRepository contactDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(String consumerCode, ConsumerUpdateV1RequestDto request) throws ConsumerNotFound {
        ConsumerDomain consumerDomain = this.consumerDao.findByConsumerCode(consumerCode);

        if (consumerDomain == null) {
            throw new ConsumerNotFound();
        }

        consumerDomain = ConsumerDomainMapper.convert(request, consumerDomain);

        this.consumerDao.save(consumerDomain);

        consumerDomain.getAddress().setConsumer(consumerDomain);
        consumerDomain.getContact().setConsumer(consumerDomain);

        this.addressRepository.save(consumerDomain.getAddress());
        this.contactDao.save(consumerDomain.getContact());
    }
}
