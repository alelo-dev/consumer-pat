package br.com.alelo.consumer.consumerpat.service;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alelo.consumer.consumerpat.dto.CheckoutDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.enums.CardType;
import br.com.alelo.consumer.consumerpat.enums.EstablishmentType;
import br.com.alelo.consumer.consumerpat.exception.InsufficientFundsException;
import br.com.alelo.consumer.consumerpat.exception.InvalidCardTypeException;
import br.com.alelo.consumer.consumerpat.exception.ResourceNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

@ExtendWith(MockitoExtension.class)
public class CheckoutServiceTest {

    @Mock
    private CardRepository cardRepository;

    @Mock
    private EstablishmentRepository establishmentRepository;

    @Mock
    private ExtractRepository extractRepository;

    private CheckoutService checkoutService;

    @BeforeEach
    public void setUp() {
        checkoutService = new CheckoutService(cardRepository, establishmentRepository, extractRepository);
    }

    @Test
    public void Save_checkout() {
        Card card = new Card("1234", CardType.FOOD, new BigDecimal("20.00"), null);
        given(cardRepository.findByCardNumber("1234")).willReturn(Optional.of(card));

        checkoutService.checkout(new CheckoutDTO("test", EstablishmentType.FOOD, "food", "1234", new BigDecimal("10.00")));

        verify(extractRepository).save(any());
        verify(cardRepository).save(any());
    }

    @Test
    public void Food_checkout_calculation() {
        Card card = new Card("1234", CardType.FOOD, new BigDecimal("20.00"), null);
        given(cardRepository.findByCardNumber("1234")).willReturn(Optional.of(card));

        checkoutService.checkout(new CheckoutDTO("test", EstablishmentType.FOOD, "food", "1234", new BigDecimal("10.00")));

        then(card.getBalance()).isEqualTo(new BigDecimal("11.00"));
    }

    @Test
    public void Fuel_checkout_calculation() {
        Card card = new Card("1234", CardType.FUEL, new BigDecimal("20.00"), null);
        given(cardRepository.findByCardNumber("1234")).willReturn(Optional.of(card));

        checkoutService.checkout(new CheckoutDTO("test", EstablishmentType.FUEL, "food", "1234", new BigDecimal("10.00")));

        then(card.getBalance()).isEqualTo(new BigDecimal("10.00"));
    }

    @Test
    public void Drugstore_checkout_calculation() {
        Card card = new Card("1234", CardType.DRUGSTORE, new BigDecimal("100.00"), null);
        given(cardRepository.findByCardNumber("1234")).willReturn(Optional.of(card));

        checkoutService.checkout(new CheckoutDTO("test", EstablishmentType.DRUGSTORE, "food", "1234", new BigDecimal("50.00")));

        then(card.getBalance()).isEqualTo(new BigDecimal("32.50"));
    }

    @Test
    public void Checkout_with_invalid_card_type_for_Fuel_establishment() {
        Card card = new Card("1234", CardType.DRUGSTORE, new BigDecimal("100.00"), null);
        given(cardRepository.findByCardNumber("1234")).willReturn(Optional.of(card));

        thenThrownBy(() -> {
            checkoutService.checkout(new CheckoutDTO("test", EstablishmentType.FUEL, "food", "1234", new BigDecimal("100.00")));
        }).isInstanceOf(InvalidCardTypeException.class);
    }

    @Test
    public void Checkout_with_invalid_card_type_for_Food_establishment() {
        Card card = new Card("1234", CardType.DRUGSTORE, new BigDecimal("100.00"), null);
        given(cardRepository.findByCardNumber("1234")).willReturn(Optional.of(card));

        thenThrownBy(() -> {
            checkoutService.checkout(new CheckoutDTO("test", EstablishmentType.FOOD, "food", "1234", new BigDecimal("100.00")));
        }).isInstanceOf(InvalidCardTypeException.class);
    }

    @Test
    public void Checkout_with_invalid_card_type_for_Drugstore_establishment() {
        Card card = new Card("1234", CardType.FOOD, new BigDecimal("100.00"), null);
        given(cardRepository.findByCardNumber("1234")).willReturn(Optional.of(card));

        thenThrownBy(() -> {
            checkoutService.checkout(new CheckoutDTO("test", EstablishmentType.DRUGSTORE, "food", "1234", new BigDecimal("100.00")));
        }).isInstanceOf(InvalidCardTypeException.class);
    }

    @Test
    public void Checkout_with_invalid_card() {
        thenThrownBy(() -> {
            checkoutService.checkout(new CheckoutDTO("test", EstablishmentType.FUEL, "food", "12346", new BigDecimal("100.00")));
        }).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void Checkout_with_insufficient_funds() {
        Card card = new Card("1234", CardType.DRUGSTORE, new BigDecimal("100.00"), null);
        given(cardRepository.findByCardNumber("1234")).willReturn(Optional.of(card));

        thenThrownBy(() -> {
            checkoutService.checkout(new CheckoutDTO("test", EstablishmentType.DRUGSTORE, "food", "1234", new BigDecimal("100.00")));
        }).isInstanceOf(InsufficientFundsException.class);
    }

}
