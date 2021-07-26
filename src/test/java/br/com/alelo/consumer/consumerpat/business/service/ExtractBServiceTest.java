package br.com.alelo.consumer.consumerpat.business.service;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.ExtractCreationException;
import br.com.alelo.consumer.consumerpat.mock.AppMock;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.when;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 25/07/2021 | 12:30
 */

@ExtendWith(SpringExtension.class)
class ExtractBServiceTest {

    @InjectMocks
    private ExtractBService extractBService;
    @Mock
    private ExtractRepository extractRepository;

    @Test
    void saveSuccess() {
        when(this.extractRepository.save(Mockito.any(Extract.class))).thenReturn(AppMock.mockExtract());
        Assertions.assertEquals(this.extractBService.save(new Extract()).getCardNumber(), AppMock.mockExtract().getCardNumber());
    }

    @Test
    void saveError() {
        when(this.extractRepository.save(Mockito.any(Extract.class))).thenThrow(NullPointerException.class);
        Assertions.assertThrows(ExtractCreationException.class, () -> {
            this.extractBService.save(new Extract());
        });
    }
}