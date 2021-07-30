package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.MockitoBaseTest;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;

import static br.com.alelo.consumer.consumerpat.builder.CardBuilder.emptyCard;
import static br.com.alelo.consumer.consumerpat.builder.TransactionBuilder.fullTransactionDTO;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ExtractServiceTest extends MockitoBaseTest {
    @Mock
    private ExtractRepository extractRepository;
    @InjectMocks
    private ExtractService target;

    @Test
    public void shouldCreate() {
        when(extractRepository.save(any(Extract.class))).thenReturn(new Extract());
        assertNotNull(target.create(fullTransactionDTO(), emptyCard(), BigDecimal.ONE));
    }

}