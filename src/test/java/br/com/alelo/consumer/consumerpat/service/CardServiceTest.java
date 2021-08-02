package br.com.alelo.consumer.consumerpat.service;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alelo.consumer.consumerpat.dto.ReloadCardDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.enums.CardType;
import br.com.alelo.consumer.consumerpat.exception.ResourceNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    private CardService cardService;

    @BeforeEach
    public void setUp() {
        cardService = new CardService(cardRepository);
    }

    @Test
    public void Reload_valid_card() {
        Card card = new Card("1234", CardType.FOOD, new BigDecimal("10.00"), null);
        given(cardRepository.findByCardNumber(eq("1234"))).willReturn(Optional.of(card));

        cardService.reloadCard(new ReloadCardDTO("1234", new BigDecimal("10.00")));

        then(card.getBalance()).isEqualTo(new BigDecimal("20.00"));
    }

    @Test
    public void Reload_invalid_card() {
        thenThrownBy(() -> {
            cardService.reloadCard(new ReloadCardDTO("1234", new BigDecimal("10.00")));
        }).isInstanceOf(ResourceNotFoundException.class);
    }

}
