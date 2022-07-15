package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.model.entity.Consumer;
import br.com.alelo.consumer.consumerpat.web.vo.consumer.ConsumerFilterVO;
import br.com.alelo.consumer.consumerpat.web.vo.consumer.NewConsumerFormVO;
import br.com.alelo.consumer.consumerpat.web.vo.consumer.UpdateConsumerFormVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConsumerService {

    Consumer findById(Long consumerId);

    Page<Consumer> findAll(ConsumerFilterVO filters, Pageable pageable);

    Consumer save(NewConsumerFormVO form);

    Consumer update(Long consumerId, UpdateConsumerFormVO form);

}
