package br.com.alelo.consumer.consumerpat.application;

import br.com.alelo.consumer.consumerpat.application.port.in.CreditAddition;
import br.com.alelo.consumer.consumerpat.application.port.out.FindCardPort;
import br.com.alelo.consumer.consumerpat.application.port.out.UpdateCardPort;
import br.com.alelo.consumer.consumerpat.common.UseCase;
import br.com.alelo.consumer.consumerpat.domain.Card;

@UseCase
public class CreditAdditionUseCase implements CreditAddition {
  
  private FindCardPort findCardPort;
  private UpdateCardPort updateCardPort;

  public CreditAdditionUseCase(final FindCardPort findCardPort, final UpdateCardPort updateCardPort) {
    this.findCardPort = findCardPort;
    this.updateCardPort = updateCardPort;
  }

  @Override
  public Card credit(Integer cardNumber, Double value) {
    
    var card = findCardPort.find(cardNumber);
    card.setBalance(card.getBalance() + value);
    return updateCardPort.updateBalance(card);

  }  
}
