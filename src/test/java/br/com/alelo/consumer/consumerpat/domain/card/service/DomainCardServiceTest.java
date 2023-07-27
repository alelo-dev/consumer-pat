package br.com.alelo.consumer.consumerpat.domain.card.service;

import br.com.alelo.consumer.consumerpat.domain.card.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.domain.consumer.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.domain.ledger.service.LedgerService;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public class DomainCardServiceTest {

    private DomainCardService domainCardService;
    private CardRepository cardRepository;
    private ConsumerService consumerService;
    private LedgerService ledgerService;

    @BeforeEach
    void setUp() {
        cardRepository = mock(CardRepository.class);
        consumerService = mock(ConsumerService.class);
        ledgerService = mock(LedgerService.class);

        domainCardService = new DomainCardService(cardRepository, consumerService, ledgerService);
    }

}
