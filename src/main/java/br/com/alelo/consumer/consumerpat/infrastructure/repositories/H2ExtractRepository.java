package br.com.alelo.consumer.consumerpat.infrastructure.repositories;

import br.com.alelo.consumer.consumerpat.domain.entities.Extract;
import br.com.alelo.consumer.consumerpat.domain.repositories.ExtractRepository;
import br.com.alelo.consumer.consumerpat.infrastructure.repositories.entities.ExtractEntity;
import br.com.alelo.consumer.consumerpat.infrastructure.repositories.jpas.ExtractJpaRepository;
import br.com.alelo.consumer.consumerpat.infrastructure.repositories.wrappers.ExtractEntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class H2ExtractRepository implements ExtractRepository {

    private final ExtractJpaRepository extractRepository;

    @Autowired
    public H2ExtractRepository(final ExtractJpaRepository extractJpaRepository) {
        this.extractRepository = extractJpaRepository;
    }

    @Override
    public void save(Extract extract) {
        ExtractEntity extractEntity
                = new ExtractEntityWrapper(new ExtractEntity()).copyFromProperties(extract);
        extractRepository.save(extractEntity);
    }

    @Override
    public List<Extract> findAll(int consumerId, int page, int size) {
        List<Extract> extracts = new ArrayList<>();

        Page<ExtractEntity> extractEntities =
                extractRepository.findAllPageable(consumerId, PageRequest.of(page, size));
        for (ExtractEntity extractEntity: extractEntities) {
            extracts.add(extractEntity.toModel());
        }
        return extracts;
    }
}