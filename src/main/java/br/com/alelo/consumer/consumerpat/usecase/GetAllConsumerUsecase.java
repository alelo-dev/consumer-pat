package br.com.alelo.consumer.consumerpat.usecase;

import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAllConsumerUsecase {
    private final ConsumerRepository repository;

}
