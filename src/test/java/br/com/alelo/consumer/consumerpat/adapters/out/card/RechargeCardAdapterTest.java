package br.com.alelo.consumer.consumerpat.adapters.out.card;

import br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer.repository.CardCustomerRepository;
import br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer.repository.entity.CardCustomerEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.AddressEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.ContactEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.CustomerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RechargeCardAdapterTest {
    private RechargeCardAdapter rechargeCardAdapter;
    private CardCustomerRepository cardCustomerRepository;

    @BeforeEach
    void setUp() {
        cardCustomerRepository = mock(CardCustomerRepository.class);

        rechargeCardAdapter = new RechargeCardAdapter(cardCustomerRepository);
    }

    @Test
    void testRechargeWithSuccess() {
        var cardNumber = "1234567812345678";
        var cardType = "FOOD";

        var contactEntity = new ContactEntity(UUID.randomUUID(),"940028786", "111111111","joaodasneves@gmail.com");
        var addressEntity = new AddressEntity(UUID.randomUUID(),"Avenida Euclides da cunha", "3", "Dracena", "Brasil", "03343000");
        var customerEntity = new CustomerEntity(UUID.randomUUID(),"Joao das neves", "22222222222", LocalDate.now(), addressEntity, contactEntity, LocalDateTime.now(), null);
        var cardCustomerEntity = new CardCustomerEntity(UUID.randomUUID(), cardType, cardNumber, BigDecimal.valueOf(300), customerEntity);

        when(cardCustomerRepository.findByCardNumber(cardNumber)).thenReturn(Optional.of(cardCustomerEntity));

        rechargeCardAdapter.recharge(cardNumber, BigDecimal.valueOf(100));

        verify(cardCustomerRepository, times(1)).save(any(CardCustomerEntity.class));
    }
}
