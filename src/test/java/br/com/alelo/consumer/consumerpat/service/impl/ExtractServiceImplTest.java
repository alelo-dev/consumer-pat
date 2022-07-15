package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.exception.ResourceNotFoundException;
import br.com.alelo.consumer.consumerpat.model.entity.Card;
import br.com.alelo.consumer.consumerpat.model.entity.Extract;
import br.com.alelo.consumer.consumerpat.model.enums.EstablishmentType;
import br.com.alelo.consumer.consumerpat.model.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.model.repository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.web.vo.extract.ExtractFilterVO;
import br.com.alelo.consumer.consumerpat.web.vo.extract.NewExtractFormVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static br.com.alelo.consumer.consumerpat.fixture.ConsumerPatFixture.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ActiveProfiles("test")
class ExtractServiceImplTest {

    private static final String CARD_NOT_FOUND_MESSAGE = "Card not found!";
    private static final String ESTABLISHMENT_NOT_ALLOWED_MESSAGE = "Establishment not allowed for the informed card!";
    private static final String INSUFFICIENT_CARD_BALANCE = "The card does not have enough balance!";

    @MockBean
    private ExtractRepository extractRepository;

    @MockBean
    private CardRepository cardRepository;

    @Autowired
    private ExtractServiceImpl extractService;

    @Test
    void findAll_WithEmptyResult_ShouldReturnEmptyResultInPaginatedList() {
        given(extractRepository.findAllByFilters(any(ExtractFilterVO.class), any(Pageable.class)))
                .willReturn(new PageImpl<>(List.of()));

        ExtractFilterVO filters = new ExtractFilterVO();

        Page<Extract> page = extractService.findAll(filters, buildPageable(filters));

        assertNotNull(page);
        assertNotNull(page.getContent());
        assertEquals(0, page.getTotalElements());
    }

    @Test
    void findAll_WithNonEmptyResult_ShouldReturnExtractInPaginatedList() {
        Extract extract = buildExtract(null, null);

        given(extractRepository.findAllByFilters(any(ExtractFilterVO.class), any(Pageable.class)))
                .willReturn(new PageImpl<>(List.of(extract)));

        ExtractFilterVO filters = new ExtractFilterVO();

        Page<Extract> page = extractService.findAll(filters, buildPageable(filters));

        assertNotNull(page);
        assertNotNull(page.getContent());
        assertEquals(1, page.getTotalElements());

        Extract extractResult = page.getContent().get(0);

        assertEquals(extract.getId(), extractResult.getId());
        assertEquals(extract.getEstablishmentId(), extractResult.getEstablishmentId());
        assertEquals(extract.getEstablishmentName(), extractResult.getEstablishmentName());
        assertEquals(extract.getProductDescription(), extractResult.getProductDescription());
        assertEquals(extract.getDateBuy(), extractResult.getDateBuy());
        assertEquals(0, extract.getValue().compareTo(extractResult.getValue()));
    }

    @Test
    void save_WithNonExistentCard_ShouldThrowBusinessException() {
        given(cardRepository.findByCardNumber(CARD_NUMBER)).willReturn(Optional.empty());

        NewExtractFormVO form = new NewExtractFormVO();
        form.setCardNumber(CARD_NUMBER);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            extractService.save(form);
        });
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(CARD_NOT_FOUND_MESSAGE));
    }

    @Test
    void save_WithCardTypeIncompatibleWithEstablishment_ShouldThrowBusinessException() {
        Card drugstoreCard = buildCard(EstablishmentType.DRUGSTORE, null);

        given(cardRepository.findByCardNumber(drugstoreCard.getNumber())).willReturn(Optional.of(drugstoreCard));

        NewExtractFormVO form = new NewExtractFormVO();
        form.setEstablishmentType(EstablishmentType.FUEL.getValue());
        form.setCardNumber(drugstoreCard.getNumber());

        Exception exception = assertThrows(BusinessException.class, () -> {
            extractService.save(form);
        });
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(ESTABLISHMENT_NOT_ALLOWED_MESSAGE));
    }

    @Test
    void save_WithValueExceedingCardBalance_ShouldThrowBusinessException() {
        Card drugstoreCard = buildCard(EstablishmentType.DRUGSTORE, null);

        NewExtractFormVO form = new NewExtractFormVO();
        form.setEstablishmentType(EstablishmentType.DRUGSTORE.getValue());
        form.setCardNumber(drugstoreCard.getNumber());
        form.setValue(drugstoreCard.getBalance().add(BigDecimal.TEN));

        given(cardRepository.findByCardNumber(drugstoreCard.getNumber())).willReturn(Optional.of(drugstoreCard));

        Exception exception = assertThrows(BusinessException.class, () -> {
            extractService.save(form);
        });
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(INSUFFICIENT_CARD_BALANCE));
    }

    @Test
    void save_WithEstablishmentTypeDrugstore_ShouldPersistExtractData() {
        EstablishmentType establishmentType = EstablishmentType.DRUGSTORE;

        BigDecimal catdBalance = BigDecimal.valueOf(1000.00);
        BigDecimal buyValue = BigDecimal.valueOf(100.00);
        BigDecimal expectedBuyValue = BigDecimal.valueOf(100.00);

        Card card = buildCard(establishmentType, null);
        card.setBalance(catdBalance);

        given(cardRepository.findByCardNumber(card.getNumber())).willReturn(Optional.of(card));
        given(extractRepository.save(any(Extract.class))).willAnswer(i -> i.getArgument(0, Extract.class));

        NewExtractFormVO form = new NewExtractFormVO();
        form.setEstablishmentType(establishmentType.getValue());
        form.setCardNumber(card.getNumber());
        form.setValue(buyValue);

        Extract extractResult = extractService.save(form);

        assertNotNull(extractResult);
        assertEquals(form.getEstablishmentId(), extractResult.getEstablishmentId());
        assertEquals(form.getEstablishmentName(), extractResult.getEstablishmentName());
        assertEquals(form.getProductDescription(), extractResult.getProductDescription());
        assertEquals(0, expectedBuyValue.compareTo(extractResult.getValue()));
        assertEquals(form.getCardNumber(), extractResult.getCard().getNumber());
    }

    @Test
    void save_WithEstablishmentTypeFood_ShouldPersistExtractData() {
        EstablishmentType establishmentType = EstablishmentType.FOOD;

        BigDecimal catdBalance = BigDecimal.valueOf(1000.00);
        BigDecimal buyValue = BigDecimal.valueOf(100.00);
        BigDecimal expectedBuyValue = BigDecimal.valueOf(90.00);

        Card card = buildCard(establishmentType, null);
        card.setBalance(catdBalance);

        given(cardRepository.findByCardNumber(card.getNumber())).willReturn(Optional.of(card));
        given(extractRepository.save(any(Extract.class))).willAnswer(i -> i.getArgument(0, Extract.class));

        NewExtractFormVO form = new NewExtractFormVO();
        form.setEstablishmentType(establishmentType.getValue());
        form.setCardNumber(card.getNumber());
        form.setValue(buyValue);

        Extract extractResult = extractService.save(form);

        assertNotNull(extractResult);
        assertEquals(form.getEstablishmentId(), extractResult.getEstablishmentId());
        assertEquals(form.getEstablishmentName(), extractResult.getEstablishmentName());
        assertEquals(form.getProductDescription(), extractResult.getProductDescription());
        assertEquals(0, expectedBuyValue.compareTo(extractResult.getValue()));
        assertEquals(form.getCardNumber(), extractResult.getCard().getNumber());
    }

    @Test
    void save_WithEstablishmentTypeFuel_ShouldPersistExtractData() {
        EstablishmentType establishmentType = EstablishmentType.FUEL;

        BigDecimal catdBalance = BigDecimal.valueOf(1000.00);
        BigDecimal buyValue = BigDecimal.valueOf(100.00);
        BigDecimal expectedBuyValue = BigDecimal.valueOf(135.00);

        Card card = buildCard(establishmentType, null);
        card.setBalance(catdBalance);

        given(cardRepository.findByCardNumber(card.getNumber())).willReturn(Optional.of(card));
        given(extractRepository.save(any(Extract.class))).willAnswer(i -> i.getArgument(0, Extract.class));

        NewExtractFormVO form = new NewExtractFormVO();
        form.setEstablishmentType(establishmentType.getValue());
        form.setCardNumber(card.getNumber());
        form.setValue(buyValue);

        Extract extractResult = extractService.save(form);

        assertNotNull(extractResult);
        assertEquals(form.getEstablishmentId(), extractResult.getEstablishmentId());
        assertEquals(form.getEstablishmentName(), extractResult.getEstablishmentName());
        assertEquals(form.getProductDescription(), extractResult.getProductDescription());
        assertEquals(0, expectedBuyValue.compareTo(extractResult.getValue()));
        assertEquals(form.getCardNumber(), extractResult.getCard().getNumber());
    }

    private Pageable buildPageable(ExtractFilterVO filters) {
        return PageRequest.of(
            filters.getPage(),
            filters.getSize(),
            Sort.by(
                Sort.Direction.fromString(filters.getSortDirection().name()),
                filters.getSortField().getValue()
            ));
    }
}