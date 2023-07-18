package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class ExtractServiceImplTest {
    @Mock
    private ExtractRepository repository;

    @InjectMocks
    private ExtractService extractService = new ExtractServiceImpl(repository);

    @Test
    void testCreation() {
        var extract = new Extract("", "", Date.from(Instant.now()), 1, 0.0);

        Mockito.when(repository.save(extract)).thenReturn(extract);

        extractService.create(extract);

        Mockito.verify(repository, Mockito.times(1)).save(extract);
    }
}