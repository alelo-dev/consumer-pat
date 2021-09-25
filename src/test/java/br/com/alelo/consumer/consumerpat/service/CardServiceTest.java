package br.com.alelo.consumer.consumerpat.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import org.junit.jupiter.api.Test;

public class CardServiceTest {

    private final CardService service = new CardService();

    @Test
    public void whenRechargeValueIsSmallerThanZero_thenThrowIllegalArgumentException() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> service.recharge(new Card(), -10));
        assertEquals("Value can't be negative", ex.getMessage());
    }

    @Test
    public void whenRechargeValueIsBiggerThanZero_thenSaveNewBalance() {
        CardRepository repository = mock(CardRepository.class);
        service.setRepository(repository);

        Card card = Card.builder()
                .balance(100)
                .build();

        service.recharge(card, 30);

        assertEquals(130, card.getBalance());
    }

}
