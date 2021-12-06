package br.com.alelo.consumer.consumerpat.adapter.out.persistence;

import java.util.ArrayList;
import java.util.List;
import br.com.alelo.consumer.consumerpat.adapter.out.persistence.mapper.ConsumerMapper;
import br.com.alelo.consumer.consumerpat.adapter.out.persistence.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.application.port.out.FindConsumerPort;
import br.com.alelo.consumer.consumerpat.application.port.out.SaveConsumerPort;
import br.com.alelo.consumer.consumerpat.application.port.out.UpdateConsumerPort;
import br.com.alelo.consumer.consumerpat.common.PersistenceAdapter;
import br.com.alelo.consumer.consumerpat.domain.Consumer;
import br.com.alelo.consumer.consumerpat.exception.ConsumerNotFoundException;

@PersistenceAdapter
public class ConsumerPersistenceAdapter
    implements SaveConsumerPort, FindConsumerPort, UpdateConsumerPort {

  private ConsumerRepository consumerRepository;

  public ConsumerPersistenceAdapter(final ConsumerRepository consumerRepository) {
    this.consumerRepository = consumerRepository;
  }

  @Override
  public Consumer save(final Consumer consumer) {

    var consumerEntity = ConsumerMapper.mapToJpaEntity(consumer);

    consumerEntity = consumerRepository.save(consumerEntity);

    return ConsumerMapper.mapToDomain(consumerEntity);
  }

  @Override
  public List<Consumer> all() {
    var consumerEntityList = consumerRepository.findAll();

    var consumerList = new ArrayList<Consumer>();

    consumerEntityList.forEach(consumerEntity -> {
      consumerList.add(ConsumerMapper.mapToDomain(consumerEntity));
    });

    return consumerList;

  }
  
  @Override
  public Consumer update(final Consumer consumer) {
        
    var consumerEntity = consumerRepository.findById(consumer.getId()).orElseThrow(() -> new ConsumerNotFoundException());
    
    consumerEntity = ConsumerMapper.merge(consumer, consumerEntity.getCards());

    consumerEntity = consumerRepository.save(consumerEntity);
    
    return ConsumerMapper.mapToDomain(consumerEntity);
    
  }

}
