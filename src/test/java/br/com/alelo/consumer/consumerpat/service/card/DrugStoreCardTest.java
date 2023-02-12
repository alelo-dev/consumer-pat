package br.com.alelo.consumer.consumerpat.service.card;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.spy;

@SpringBootTest
public class DrugStoreCardTest {

    @Mock
    private ConsumerRepository consumerRepository;

    @MockBean
    private CardOperationFactory cardOperationFactory;

    private Consumer consumerMock;

    private CardOperation card;

    @BeforeEach
    public void initEach(){
        MockitoAnnotations.openMocks(this);

        CardTypeEnum establishmentType = CardTypeEnum.DrugStore;
        DrugStoreCard drugstoreCard = new DrugStoreCard(consumerRepository);
        DrugStoreCard drugstoreCardSpy = spy(drugstoreCard);

        Mockito.when(cardOperationFactory.findType(establishmentType)).thenReturn(drugstoreCardSpy);
        card = cardOperationFactory.findType(establishmentType);

        consumerMock = Consumer.builder()
                .id(1)
                .name("Test Name")
                .documentNumber(9999999)
                .birthDate(LocalDate.now())
                .mobilePhoneNumber(11999999999L)
                .residencePhoneNumber(1199999999L)
                .phoneNumber(1199999999L)
                .email("test@gmail.com")
                .street("Rua Teste")
                .number (146)
                .city("Sao Paulo")
                .country("Brasil")
                .postalCode(3940030)
                .drugstoreNumber(999999999999999999L)
                .foodCardBalance(BigDecimal.ZERO)
                .fuelCardBalance(BigDecimal.ZERO)
                .drugstoreCardBalance(BigDecimal.ZERO)
                .build();

    }

    @Test
    public void shouldNotChangeValueWhenCalculateDrugstoreCardDebitValue() {

        BigDecimal value = new BigDecimal("100.50");

        BigDecimal debitValue = card.calculateDebitValue(value);

        assertThat(debitValue, comparesEqualTo(new BigDecimal("100.50")));

    }

    @Test
    public void shouldDebit() {

        long cardNumber = 999999999999999999L;
        consumerMock.setDrugstoreCardBalance(new BigDecimal("100"));
        BigDecimal value = new BigDecimal("10");

        Mockito.when(consumerRepository.findByDrugstoreNumber(cardNumber)).thenReturn(Optional.of(consumerMock));

        Consumer consumer = card.debit(cardNumber, value);
        assertThat(consumer.getDrugstoreCardBalance(), comparesEqualTo(new BigDecimal("90")));

        Mockito.when(consumerRepository.findByDrugstoreNumber(cardNumber)).thenReturn(Optional.of(consumer));

        value = new BigDecimal("20");
        consumer = card.debit(cardNumber, value);
        assertThat(consumer.getDrugstoreCardBalance(), comparesEqualTo(new BigDecimal("70")));

    }

    @Test
    public void shouldCredit() {

        BigDecimal value = new BigDecimal("100");

        Consumer consumer = card.credit(consumerMock, value);
        assertThat(consumer.getDrugstoreCardBalance(), comparesEqualTo(new BigDecimal("100")));

        value = new BigDecimal("20");
        consumer = card.credit(consumer, value);
        assertThat(consumer.getDrugstoreCardBalance(), comparesEqualTo(new BigDecimal("120")));

    }

    @Test
    public void shouldThrowExceptionWhenDebitAndConsumerNotFound() {

        long cardNumber = 999999999999999999L;
        BigDecimal value = new BigDecimal("100.50");

        Mockito.when(consumerRepository.findByFoodCardNumber(cardNumber)).thenReturn(Optional.ofNullable(null));

        try {
            card.debit(cardNumber, value);
            fail("Should have thrown an exception");
        }catch (Exception e){
            assertThat(e.getMessage(), is("Consumer not found"));
        }

    }

    @Test
    public void shouldThrowExceptionWhenDebitAndInsufficientBalance() {

        long cardNumber = 999999999999999999L;
        BigDecimal value = new BigDecimal("100.50");

        Mockito.when(consumerRepository.findByDrugstoreNumber(cardNumber)).thenReturn(Optional.of(consumerMock));

        try {
            card.debit(cardNumber, value);
            fail("Should have thrown an exception");
        }catch (Exception e){
            assertThat(e.getMessage(), is("Insufficient balance"));
        }

    }

}