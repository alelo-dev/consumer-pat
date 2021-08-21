package br.com.alelo.consumer.consumerpat.usecase.impl;

import br.com.alelo.consumer.consumerpat.dataprovider.dao.AddressDao;
import br.com.alelo.consumer.consumerpat.dataprovider.dao.ConsumerDao;
import br.com.alelo.consumer.consumerpat.dataprovider.dao.ContactDao;
import br.com.alelo.consumer.consumerpat.dataprovider.entity.ConsumerEntity;
import br.com.alelo.consumer.consumerpat.dto.v1.request.ConsumerUpdateV1RequestDto;
import br.com.alelo.consumer.consumerpat.exception.ConsumerNotFound;
import br.com.alelo.consumer.consumerpat.mapper.entity.ConsumerEntityMapper;
import br.com.alelo.consumer.consumerpat.usecase.ConsumerUpdateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerUpdateUseCaseImpl implements ConsumerUpdateUseCase {

    @Autowired
    private ConsumerDao consumerDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private ContactDao contactDao;

    @Override
    public void update(String consumerCode, ConsumerUpdateV1RequestDto request) throws ConsumerNotFound {
        ConsumerEntity byConsumerCode = this.consumerDao.findByConsumerCode(consumerCode);

        if (byConsumerCode == null) {
            throw new ConsumerNotFound();
        }

        //Realizar o set dos id
        ConsumerEntity consumerEntity = ConsumerEntityMapper.convert(request);
        this.consumerDao.save(consumerEntity);
        this.addressDao.save(consumerEntity.getAddress());
        this.contactDao.save(consumerEntity.getContact());
    }
}
