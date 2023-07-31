package br.com.alelo.consumer.consumerpat.infrastructure.repository.consumer;

import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.consumer.repository.DomainConsumerRepository;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.consumer.converter.ConsumerConverter;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.consumer.entity.ConsumerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ConsumerRepository implements DomainConsumerRepository {

    private final ConsumerJpaRepository consumerRepository;
    private final ConsumerConverter consumerConverter;

    @Override
    public Optional<Consumer> findById(UUID id) {
        var consumerFound = consumerRepository.findById(id);
        return consumerFound.map(consumerConverter::toDomain);
    }

    @Override
    public UUID save(Consumer consumer, boolean isNew) {
        var consumerEntity = consumerConverter.toEntity(consumer);
        if (isNew) {
            consumerEntity.setCreatedAt(LocalDateTime.now());
        }
        consumerEntity.setUpdatedAt(LocalDateTime.now());
        return consumerRepository.save(consumerEntity).getId();
    }

    @Override
    public Page<Consumer> listAll(Pageable consumerPageable) {
        Page<ConsumerEntity> allCustomers = consumerRepository.findAll(consumerPageable);
        return allCustomers.map(consumerConverter::toDomain);
    }
}
