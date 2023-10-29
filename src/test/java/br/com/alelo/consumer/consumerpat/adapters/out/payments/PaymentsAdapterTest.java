package br.com.alelo.consumer.consumerpat.adapters.out.payments;

import br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer.repository.CardCustomerRepository;
import br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer.repository.entity.CardCustomerEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.AddressEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.ContactEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.CustomerEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.payments.repository.PaymentsRepository;
import br.com.alelo.consumer.consumerpat.adapters.out.payments.repository.mapper.PaymentsEntityMapper;
import br.com.alelo.consumer.consumerpat.application.core.domain.payments.Establishment;
import br.com.alelo.consumer.consumerpat.application.core.domain.payments.Payments;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PaymentsAdapterTest {

    private PaymentsAdapter paymentsAdapter;
    private CardCustomerRepository cardCustomerRepository;
    private PaymentsRepository paymentsRepository;
    private PaymentsEntityMapper paymentsEntityMapper;

    @BeforeEach
    void setUp() {
        cardCustomerRepository = mock(CardCustomerRepository.class);
        paymentsRepository = mock(PaymentsRepository.class);
        paymentsEntityMapper = mock(PaymentsEntityMapper.class);

        paymentsAdapter = new PaymentsAdapter(cardCustomerRepository, paymentsRepository, paymentsEntityMapper);
    }

    @Test
    void testRegisterPaymentFoodSuccess() {
        var cardNumber = "32312312312312312312";
        var cardType = "FOOD";
        Establishment establishment = new Establishment("MacDonalds", cardType);
        var payments = new Payments(UUID.randomUUID(), establishment, "Food", LocalDate.now(), cardNumber,BigDecimal.valueOf(150));

        var contactEntity = new ContactEntity(UUID.randomUUID(),"940028786", "111111111","joaodasneves@gmail.com");
        var addressEntity = new AddressEntity(UUID.randomUUID(),"Avenida Euclides da cunha", "3", "Dracena", "Brasil", "03343000");
        var customerEntity = new CustomerEntity(UUID.randomUUID(),"Joao das neves", "22222222222", LocalDate.now(), addressEntity, contactEntity, LocalDateTime.now(), null);
        var cardCustomerEntity = new CardCustomerEntity(UUID.randomUUID(), cardType, cardNumber, BigDecimal.valueOf(300), customerEntity);

        when(cardCustomerRepository.findByCardNumber(cardNumber)).thenReturn(Optional.of(cardCustomerEntity));

        paymentsAdapter.payment(payments);

        verify(cardCustomerRepository, times(1)).save(any(CardCustomerEntity.class));
        verify(paymentsRepository, times(1)).save(paymentsEntityMapper.toPaymentsEntity(any(Payments.class)));
    }

    @Test
    void testRegisterPaymentDrugStoreSuccess() {
        var cardNumber = "32312312312312312312";
        var cardType = "DRUGSTORE";
        Establishment establishment = new Establishment("Raia", cardType);;
        var payments = new Payments(UUID.randomUUID(), establishment, "Pharmacy", LocalDate.now(), cardNumber,BigDecimal.valueOf(150));

        var contactEntity = new ContactEntity(UUID.randomUUID(),"940028786", "111111111","joaodasneves@gmail.com");
        var addressEntity = new AddressEntity(UUID.randomUUID(),"Avenida Euclides da cunha", "3", "Dracena", "Brasil", "03343000");
        var customerEntity = new CustomerEntity(UUID.randomUUID(),"Joao das neves", "22222222222", LocalDate.now(), addressEntity, contactEntity, LocalDateTime.now(), null);
        var cardCustomerEntity = new CardCustomerEntity(UUID.randomUUID(), cardType, cardNumber, BigDecimal.valueOf(300), customerEntity);

        when(cardCustomerRepository.findByCardNumber(cardNumber)).thenReturn(Optional.of(cardCustomerEntity));

        paymentsAdapter.payment(payments);

        verify(cardCustomerRepository, times(1)).save(any(CardCustomerEntity.class));
        verify(paymentsRepository, times(1)).save(paymentsEntityMapper.toPaymentsEntity(any(Payments.class)));
    }

    @Test
    void testRegisterPaymentFuelSuccess() {
        var cardNumber = "1234567812345678";
        var cardType = "FUEL";
        Establishment establishment = new Establishment("Rede Duque", cardType);;
        var payments = new Payments(UUID.randomUUID(), establishment, "Gas Station", LocalDate.now(), cardNumber,BigDecimal.valueOf(150));

        var contactEntity = new ContactEntity(UUID.randomUUID(),"940028786", "111111111","joaodasneves@gmail.com");
        var addressEntity = new AddressEntity(UUID.randomUUID(),"Avenida Euclides da cunha", "3", "Dracena", "Brasil", "03343000");
        var customerEntity = new CustomerEntity(UUID.randomUUID(),"Joao das neves", "22222222222", LocalDate.now(), addressEntity, contactEntity, LocalDateTime.now(), null);
        var cardCustomerEntity = new CardCustomerEntity(UUID.randomUUID(), cardType, cardNumber, BigDecimal.valueOf(300), customerEntity);

        when(cardCustomerRepository.findByCardNumber(cardNumber)).thenReturn(Optional.of(cardCustomerEntity));

        paymentsAdapter.payment(payments);

        verify(cardCustomerRepository, times(1)).save(any(CardCustomerEntity.class));
        verify(paymentsRepository, times(1)).save(paymentsEntityMapper.toPaymentsEntity(any(Payments.class)));
    }

    @Test
    void testRegisterPaymentNotFound() {
        var cardNumber = "1234567812345678";
        var cardType = "CLOTHES";
        Establishment establishment = new Establishment("Renner", cardType);;
        var payments = new Payments(UUID.randomUUID(), establishment, "Department Store", LocalDate.now(), cardNumber,BigDecimal.valueOf(150));

        var contactEntity = new ContactEntity(UUID.randomUUID(),"940028786", "111111111","joaodasneves@gmail.com");
        var addressEntity = new AddressEntity(UUID.randomUUID(),"Avenida Euclides da cunha", "3", "Dracena", "Brasil", "03343000");
        var customerEntity = new CustomerEntity(UUID.randomUUID(),"Joao das neves", "555555555", LocalDate.now(), addressEntity, contactEntity, LocalDateTime.now(), null);
        var cardCustomerEntity = new CardCustomerEntity(UUID.randomUUID(), cardType, cardNumber, BigDecimal.valueOf(300), customerEntity);

        when(cardCustomerRepository.findByCardNumber(cardNumber)).thenReturn(Optional.of(cardCustomerEntity));


        RuntimeException exception = assertThrows(RuntimeException.class, () -> paymentsAdapter.payment(payments));
        assertEquals("Establishment not authorized", exception.getMessage());
    }
}

