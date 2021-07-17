package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.RequestBuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.repository.IExtractRepository;
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

import static br.com.alelo.consumer.consumerpat.enums.CardType.FOOD;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ExtractServiceTest {

    @Autowired
    private IExtractService service;

    @MockBean
    private IExtractRepository repository;

    private static final String PRODUCT_DESCRIPTION = "Breakfast";
    private static final LocalDate NOW = LocalDate.now();
    private static final BigDecimal TWENTY = new BigDecimal(20);

    private Card card;

    @BeforeEach
    void setup() {
        card = Card.builder()
                .id(1L)
                .balance(BigDecimal.TEN)
                .cardType(FOOD)
                .build();

        when(repository.save(any())).thenReturn(Extract.builder().build());
    }

    @Test
    void save_whenExtractOk_thenReturnSuccess() {
        final Extract extract = service.save(card, RequestBuyDTO.builder().build(), NOW);
        assertNotNull(extract);
    }

    @Test
    void save_whenSaveWithProblem_thenResponseStatusException() {
        when(repository.save(any())).thenThrow(ResponseStatusException.class);
        assertThrows(ResponseStatusException.class, () -> service.save(card, RequestBuyDTO.builder().build(), NOW));
    }
}
