package br.com.alelo.consumer.consumerpat.business.service.impl;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 24/07/2021 | 14:18
 */

import br.com.alelo.consumer.consumerpat.business.service.AbstractBService;
import br.com.alelo.consumer.consumerpat.entity.ConsumerAleloCard;
import br.com.alelo.consumer.consumerpat.exception.ConsumerRecoverObjectCustomerException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerAleloCardRepository;
import br.com.alelo.consumer.consumerpat.util.Applog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AleloCardBServiceImpl extends AbstractBService<ConsumerAleloCard> {

    private final ConsumerAleloCardRepository consumerAleloCardRepository;

    public AleloCardBServiceImpl(ConsumerAleloCardRepository consumerAleloCardRepository) {
        super(consumerAleloCardRepository, AleloCardBServiceImpl.class);
        this.consumerAleloCardRepository = consumerAleloCardRepository;
    }

    public ConsumerAleloCard recoverByCardNumber(String value){
        Applog.infoStart(this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        try{
            return this.consumerAleloCardRepository.findByNumber(value).orElse(null);
        }catch (Exception e){
            throw new ConsumerRecoverObjectCustomerException();
        }finally {
            Applog.infoEnd(this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        }
    }

}
