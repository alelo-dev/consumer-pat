package br.com.alelo.consumerpat.core.usecase.impl;

import br.com.alelo.consumerpat.core.dataprovider.dao.AddressDao;
import br.com.alelo.consumerpat.core.dataprovider.dao.ConsumerDao;
import br.com.alelo.consumerpat.core.dataprovider.dao.ContactDao;
import br.com.alelo.consumerpat.core.dataprovider.entity.ConsumerEntity;
import br.com.alelo.consumerpat.core.exception.ConsumerNotFound;
import br.com.alelo.consumerpat.core.mapper.entity.ConsumerEntityMapper;
import br.com.alelo.consumerpat.core.usecase.ConsumerUpdateUseCase;
import br.com.alelo.consumerpat.core.dto.v1.request.ConsumerUpdateV1RequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConsumerUpdateUseCaseImpl implements ConsumerUpdateUseCase {

    @Autowired
    private ConsumerDao consumerDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private ContactDao contactDao;

    @Override
    @Transactional
    public void update(String consumerCode, ConsumerUpdateV1RequestDto request) throws ConsumerNotFound {
        ConsumerEntity byConsumerCode = this.consumerDao.findByConsumerCode(consumerCode);

        if (byConsumerCode == null) {
            throw new ConsumerNotFound();
        }

        ConsumerEntity consumerEntity = ConsumerEntityMapper.convert(request);
        consumerEntity.setId(byConsumerCode.getId());
        consumerEntity.setConsumerCode(byConsumerCode.getConsumerCode());
        consumerEntity.setDocument(byConsumerCode.getDocument());
        consumerEntity.getAddress().setId(byConsumerCode.getAddress().getId());
        consumerEntity.getContact().setId(byConsumerCode.getContact().getId());
        consumerEntity.getAddress().setConsumer(consumerEntity);
        consumerEntity.getContact().setConsumer(consumerEntity);

        this.consumerDao.save(consumerEntity);
        this.addressDao.save(consumerEntity.getAddress());
        this.contactDao.save(consumerEntity.getContact());
    }
}
