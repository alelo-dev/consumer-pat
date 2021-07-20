package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;

import static br.com.alelo.consumer.consumerpat.enums.CardType.FUEL;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@SuppressWarnings("squid:S5778")
class ExtractServiceTest {

    @Autowired
    private ExtractService extractService;

    @MockBean
    private ExtractRepository extractRepository;

    private Card card;

    @BeforeEach
    void setup() {
        card = Card.builder()
                .id(1L)
                .balance(BigDecimal.ONE)
                .cardType(FUEL)
                .build();

        when(extractRepository.save(any())).thenReturn(Extract.builder().build());
    }

    @Test
    void Save_when_extract_is_ok() {
        final Extract extract = extractService.save(card, BuyDTO.builder().build(), LocalDate.now());
        assertNotNull(extract);
    }

    @Test
    void Save_when_there_is_a_problem() {
        when(extractRepository.save(any())).thenThrow(ResponseStatusException.class);
        assertThrows(ResponseStatusException.class, () -> extractService.save(card, BuyDTO.builder().build(), LocalDate.now()));
    }
}
