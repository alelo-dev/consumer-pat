package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.model.request.BuyRequest;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static br.com.alelo.consumer.consumerpat.utils.types.CardAndEstablishmentType.DRUGSTORE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ExtractServiceTest {

    @Mock
    private ExtractRepository extractRepository;

    @InjectMocks
    private ExtractService extractService;

    private Card card;
    private Establishment establishment;
    private BuyRequest buyRequest;
    private Extract extract;

    @BeforeEach
    public void setUp() {

        card = Card.builder().id(1L).number("123").type(DRUGSTORE).balance(100.0).build();
        establishment = Establishment.builder().id(1L).name("test").type(DRUGSTORE).build();
        buyRequest =
                BuyRequest.builder().establishment(establishment).cardNumber(card.getNumber()).productDescription(
                        "product test").value(10.0).build();
        extract =
                Extract.builder().id(1L).productDescription(buyRequest.getProductDescription()).value(buyRequest.getValue()).card(card).establishment(establishment).build();
    }

    @Test
    public void testCreateExtractWithoutDate() {

        extractService.createExtract(buyRequest, establishment, card);

        final ArgumentCaptor<Extract> argumentExtract = ArgumentCaptor.forClass(Extract.class);
        verify(extractRepository, times(1)).save(argumentExtract.capture());
        assertEquals(establishment, argumentExtract.getValue().getEstablishment());
        assertEquals(card, argumentExtract.getValue().getCard());
        assertNotNull(argumentExtract.getValue().getDateBuy());
    }

    @Test
    public void testCreateExtractWithDate() {

        final Date testDate = new Date();
        buyRequest.setDateBuy(testDate);
        extractService.createExtract(buyRequest, establishment, card);

        final ArgumentCaptor<Extract> argumentExtract = ArgumentCaptor.forClass(Extract.class);
        verify(extractRepository, times(1)).save(argumentExtract.capture());
        assertEquals(establishment, argumentExtract.getValue().getEstablishment());
        assertEquals(card, argumentExtract.getValue().getCard());
        assertEquals(testDate, argumentExtract.getValue().getDateBuy());
    }

    @Test
    public void testFindAll() {

        when(extractRepository.findAll()).thenReturn(List.of(extract));
        final List<Extract> extracts = extractService.findAll();

        assertEquals(1, extracts.size());
    }
}