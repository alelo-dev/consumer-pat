package br.com.alelo.consumer.consumerpat.usecase.impl;

import br.com.alelo.consumer.consumerpat.dataprovider.dao.ConsumerDao;
import br.com.alelo.consumer.consumerpat.domain.ConsumerDomain;
import br.com.alelo.consumer.consumerpat.dto.v1.request.ConsumerCreateV1RequestDto;
import br.com.alelo.consumer.consumerpat.exception.BadRequestException;
import br.com.alelo.consumer.consumerpat.exception.ConsumerEmptyException;
import br.com.alelo.consumer.consumerpat.mapper.domain.ConsumerDomainMapper;
import br.com.alelo.consumer.consumerpat.mapper.entity.ConsumerEntityMapper;
import br.com.alelo.consumer.consumerpat.usecase.ConsumerCreateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerCreateUseCaseImpl implements ConsumerCreateUseCase {

    @Autowired
    private ConsumerDao consumerDao;

    @Override
    public String create(ConsumerCreateV1RequestDto request) throws BadRequestException {
        ConsumerDomain consumerDomain = ConsumerDomainMapper.convert(request);

        if (consumerDomain == null) {
            throw new ConsumerEmptyException();
        }

        consumerDomain.validateRequiredFields();
        consumerDomain.generateConsumerCode();

        this.consumerDao.save(ConsumerEntityMapper.convert(consumerDomain));

        return consumerDomain.getConsumerCode();
    }
}
