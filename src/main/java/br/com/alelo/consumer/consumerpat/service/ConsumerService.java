package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.adapter.ConsumerAdapter;
import br.com.alelo.consumer.consumerpat.entity.card.Card;
import br.com.alelo.consumer.consumerpat.entity.consumer.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.vo.ConsumerVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository repository;


    public Page<ConsumerVo> listAllConsumers(Pageable page) {

        Page<Consumer> pages = repository.findAll(Optional.ofNullable(page).orElse(Pageable.unpaged()));
        Page<ConsumerVo> result = new PageImpl<>(ConsumerAdapter.modelToVo(pages.getContent()));

        return result;
    }

    public ConsumerVo createConsumer(ConsumerVo consumerVo) {

        Consumer consumer = ConsumerAdapter.voToModel(consumerVo);
        consumerVo = ConsumerAdapter.modelToVo(repository.saveAndFlush(consumer));

        return consumerVo;
    }

    public ConsumerVo updateConsumer(ConsumerVo consumerVo, Long id) {

        try{
            repository.findById(id.intValue()).orElseThrow(() -> new Exception("Consumidor não encontrado"));
            Consumer consumer = ConsumerAdapter.voToModel(consumerVo);
            consumerVo = ConsumerAdapter.modelToVo(repository.saveAndFlush(consumer));

        }catch (Exception exception){
            log.error(exception.getMessage());
        }
        return consumerVo;
    }


}
