package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.config.ModelMapperConfig;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.mapper.PurchaseMapper;
import br.com.alelo.consumer.consumerpat.mock.MockCardDomain;
import br.com.alelo.consumer.consumerpat.mock.MockPurchaseDomain;
import br.com.alelo.consumer.consumerpat.response.ExtractResponse;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.impl.PurchaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig(classes = {ModelMapperConfig.class})
public class PurchaseServiceTest {

    @InjectMocks
    private PurchaseServiceImpl purchaseService;

    @Mock
    private ExtractRepository extractRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Mock
    private PurchaseMapper purchaseMapper;

    @Mock
    private CardRepository cardRepository;

    @BeforeEach
    public void init() {
        ReflectionTestUtils.setField(purchaseService, "extractRepository", extractRepository);
    }


    @Test
    @DisplayName("PurchaseServiceTest Test Purchase success")
    void testPurchaseSucess() throws BusinessException {
        when(cardRepository.findByCardNumber(any())).thenReturn(Optional.of(MockCardDomain.buildCard()));
        when(purchaseMapper.toEntity(any(),any())).thenReturn(MockPurchaseDomain.buildExtract());
        when(cardRepository.save(any())).thenReturn(MockCardDomain.buildCard());
        when(extractRepository.save(any())).thenReturn(MockPurchaseDomain.buildExtract());
        when(purchaseMapper.toResponse(any())).thenReturn(MockPurchaseDomain.buildExtractResponse());

        ExtractResponse purchase = purchaseService.purchase(MockPurchaseDomain.buildPurchaseRequest());

        assertNotNull(purchase);
    }

    @Test
    @DisplayName("PurchaseServiceTest Test Purchase Card Not Found")
    void testPurchaseCardNotFound() throws BusinessException {
        when(cardRepository.findByCardNumber(any())).thenReturn(Optional.empty());

        BusinessException exception = assertThrows(BusinessException.class, () -> purchaseService.purchase(MockPurchaseDomain.buildPurchaseRequest()));

        assertNotNull(exception);
        assertEquals("Card not found", exception.getMessage());
    }

    @Test
    @DisplayName("PurchaseServiceTest Test Purchase Card Inactive")
    void testPurchaseCardInactive() throws BusinessException {
        when(cardRepository.findByCardNumber(any())).thenReturn(Optional.of(MockCardDomain.buildCardInactive()));

        BusinessException exception = assertThrows(BusinessException.class, () -> purchaseService.purchase(MockPurchaseDomain.buildPurchaseRequest()));

        assertNotNull(exception);
        assertEquals("Purchase denied, inactive card", exception.getMessage());
    }

    @Test
    @DisplayName("PurchaseServiceTest Test Purchase Card Limit Down")
    void testPurchaseCardLimitDown() throws BusinessException {
        when(cardRepository.findByCardNumber(any())).thenReturn(Optional.of(MockCardDomain.buildCardNoLimit()));

        BusinessException exception = assertThrows(BusinessException.class, () -> purchaseService.purchase(MockPurchaseDomain.buildPurchaseRequest()));

        assertNotNull(exception);
        assertEquals("No limit for purchase", exception.getMessage());
    }

    @Test
    @DisplayName("PurchaseServiceTest Test Purchase Card Diferent Food Error")
    void testPurchaseCardDiferentFoodError() throws BusinessException {
        when(cardRepository.findByCardNumber(any())).thenReturn(Optional.of(MockCardDomain.buildCardFuel()));
        when(purchaseMapper.toEntity(any(),any())).thenReturn(MockPurchaseDomain.buildExtract());

        BusinessException exception = assertThrows(BusinessException.class, () -> purchaseService.purchase(MockPurchaseDomain.buildPurchaseRequest()));

        assertNotNull(exception);
        assertEquals("This card is not accepted at this establishment", exception.getMessage());
    }

    @Test
    @DisplayName("PurchaseServiceTest Test Purchase Card Diferent Fuel Error")
    void testPurchaseCardDiferentFuelError() throws BusinessException {
        when(cardRepository.findByCardNumber(any())).thenReturn(Optional.of(MockCardDomain.buildCard()));
        when(purchaseMapper.toEntity(any(),any())).thenReturn(MockPurchaseDomain.buildExtractFuel());

        BusinessException exception = assertThrows(BusinessException.class, () -> purchaseService.purchase(MockPurchaseDomain.buildPurchaseRequestFuel()));

        assertNotNull(exception);
        assertEquals("This card is not accepted at this establishment", exception.getMessage());
    }

    @Test
    @DisplayName("PurchaseServiceTest Test Purchase Card Diferent Fuel Error")
    void testPurchaseCardDiferentDrugError() throws BusinessException {
        when(cardRepository.findByCardNumber(any())).thenReturn(Optional.of(MockCardDomain.buildCard()));
        when(purchaseMapper.toEntity(any(),any())).thenReturn(MockPurchaseDomain.buildExtractDrug());

        BusinessException exception = assertThrows(BusinessException.class, () -> purchaseService.purchase(MockPurchaseDomain.buildPurchaseRequestDrug()));

        assertNotNull(exception);
        assertEquals("This card is not accepted at this establishment", exception.getMessage());
    }

    @Test
    @DisplayName("PurchaseServiceTest Test Get By Purchase Code Sucess")
    void testGetByPurchaseCodeSucess() throws BusinessException {
        when(extractRepository.findByPurchaseCode(any())).thenReturn(Optional.of(MockPurchaseDomain.buildExtract()));
        when(purchaseMapper.toResponse(any())).thenReturn(MockPurchaseDomain.buildExtractResponse());

        ExtractResponse byPurchaseCode = purchaseService.getByPurchaseCode("f940e98a-db2d-4276-ae0b-fcfb5466a121");

        assertNotNull(byPurchaseCode);
    }


    @Test
    @DisplayName("PurchaseServiceTest Test Get By Purchase Code not found")
    void testGetByPurchaseCodeNotFound() throws BusinessException {
        when(extractRepository.findByPurchaseCode(any())).thenReturn(Optional.empty());

        BusinessException exception = assertThrows(BusinessException.class, () -> purchaseService.getByPurchaseCode("f940e98a-db2d-4276-ae0b-fcfb5466a121"));

        assertNotNull(exception);
        assertEquals("Extract not found", exception.getMessage());
    }



}
