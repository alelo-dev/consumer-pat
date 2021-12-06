package br.com.alelo.consumer.consumerpat.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import br.com.alelo.consumer.consumerpat.application.CreditAdditionUseCase;
import br.com.alelo.consumer.consumerpat.application.port.out.FindCardPort;
import br.com.alelo.consumer.consumerpat.application.port.out.UpdateCardPort;
import br.com.alelo.consumer.consumerpat.domain.Card;
import br.com.alelo.consumer.consumerpat.exception.CardNotFoundException;

public class CreditAdditionUseCaseTest {

  @Mock
  private FindCardPort findCardPort;
  @Mock
  private UpdateCardPort updateCardPort;

  private CreditAdditionUseCase creditAdditionUseCase;

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
    creditAdditionUseCase = new CreditAdditionUseCase(findCardPort, updateCardPort);
  }

  @Test
  public void creditSuccess() {

    var cardNumber = 123;
    var value = 10.0;

    var card = Card.builder().balance(10.0).number(cardNumber).build();

    when(findCardPort.find(Mockito.any())).thenReturn(card);
    when(updateCardPort.updateBalance(Mockito.any())).thenReturn(card);

    creditAdditionUseCase.credit(cardNumber, value);

    Mockito.verify(findCardPort, Mockito.atLeastOnce()).find(Mockito.any());
    Mockito.verify(updateCardPort, Mockito.atLeastOnce()).updateBalance(Mockito.any());
    assertEquals(card.getBalance(), 20.0);

  }

  @Test
  public void cardNotFound() {

    var cardNumber = 123;
    var value = 10.0;

    when(findCardPort.find(Mockito.any())).thenThrow(CardNotFoundException.class);

    Assertions.assertThrows(CardNotFoundException.class, () -> {
      creditAdditionUseCase.credit(cardNumber, value);
    });

  }
}
