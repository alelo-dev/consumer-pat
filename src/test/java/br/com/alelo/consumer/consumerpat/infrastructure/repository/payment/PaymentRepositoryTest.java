package br.com.alelo.consumer.consumerpat.infrastructure.repository.payment;

import br.com.alelo.consumer.consumerpat.domain.card.entity.CardNumber;
import br.com.alelo.consumer.consumerpat.domain.payment.entity.Establishment;
import br.com.alelo.consumer.consumerpat.domain.payment.entity.EstablishmentType;
import br.com.alelo.consumer.consumerpat.domain.payment.entity.Payment;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.payment.converter.PaymentConverter;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.payment.entity.EstablishmentEntity;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.payment.entity.PaymentEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PaymentRepositoryTest {

    @InjectMocks
    private PaymentRepository paymentRepository;

    @Mock
    private PaymentJpaRepository paymentJpaRepository;

    @Mock
    private PaymentConverter paymentConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSavePayment() {
        UUID paymentId = UUID.randomUUID();
        Establishment establishment = new Establishment("Restaurant", EstablishmentType.FOOD);
        String productDescription = "Lunch";
        LocalDate buyDate = LocalDate.now();
        CardNumber cardNumber = new CardNumber("1234567890123456");
        BigDecimal amount = BigDecimal.valueOf(50.0);

        Payment payment = new Payment(establishment, productDescription, buyDate, cardNumber, amount);
        payment.addId(paymentId);
        PaymentEntity paymentEntity = PaymentEntity.builder()
                .id(paymentId)
                .establishment(EstablishmentEntity.builder()
                        .establishmentName(establishment.getEstablishmentName())
                        .establishmentType(establishment.getEstablishmentType())
                        .build())
                .productDescription(productDescription)
                .buyDate(buyDate)
                .cardNumber(cardNumber.getCardNumber())
                .amount(amount)
                .createdAt(LocalDateTime.now())
                .build();

        when(paymentConverter.toEntity(any(Payment.class))).thenReturn(paymentEntity);

        paymentRepository.save(payment);

        verify(paymentConverter).toEntity(payment);
        verify(paymentJpaRepository).save(paymentEntity);
    }
}
