package br.com.alelo.consumer.consumerpat.adapter.out.persistence;

import br.com.alelo.consumer.consumerpat.adapter.out.persistence.mapper.ExtractMapper;
import br.com.alelo.consumer.consumerpat.adapter.out.persistence.repository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.application.port.out.SaveExtractPort;
import br.com.alelo.consumer.consumerpat.common.PersistenceAdapter;
import br.com.alelo.consumer.consumerpat.domain.Extract;

@PersistenceAdapter
public class ExtractPersistenceAdapter implements SaveExtractPort {
  
  private ExtractRepository extractRepository;

  public ExtractPersistenceAdapter(final ExtractRepository extractRepository) {
    this.extractRepository = extractRepository;
  }

  @Override
  public Extract save(final Extract extract) {
    
    var extractEntity = ExtractMapper.mapToJpaEntity(extract);
    
    extractRepository.save(extractEntity);

    return extract;
  }

}
