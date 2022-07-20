package br.com.alelo.consumer.consumerpat.usecase;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateConsumerUsecase {

    private final ConsumerRepository repository;

    public void execute(Consumer consumer) {
        repository.save(consumer);
    }
}
