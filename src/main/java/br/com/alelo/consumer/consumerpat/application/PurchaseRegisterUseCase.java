package br.com.alelo.consumer.consumerpat.application;

import java.util.Objects;
import br.com.alelo.consumer.consumerpat.application.port.in.PurchaseRegister;
import br.com.alelo.consumer.consumerpat.application.port.out.FindCardPort;
import br.com.alelo.consumer.consumerpat.application.port.out.SaveExtractPort;
import br.com.alelo.consumer.consumerpat.application.port.out.UpdateCardPort;
import br.com.alelo.consumer.consumerpat.common.UseCase;
import br.com.alelo.consumer.consumerpat.domain.Extract;
import br.com.alelo.consumer.consumerpat.domain.PurchaseOccurrence;
import br.com.alelo.consumer.consumerpat.exception.IncompatibleCardTypeException;
import br.com.alelo.consumer.consumerpat.exception.InvalidBalanceValueException;
import br.com.alelo.consumer.consumerpat.exception.InvalidPurchaseOccurrenceException;

@UseCase
public class PurchaseRegisterUseCase implements PurchaseRegister {

  private FindCardPort findCardPort;
  private UpdateCardPort updateCardPort;
  private SaveExtractPort saveExtractPort;
  private EstablishmentFactory establishmentFactory;

  public PurchaseRegisterUseCase(final FindCardPort findCardPort,
      final UpdateCardPort updateCardPort, final EstablishmentFactory establishmentFactory,
      final SaveExtractPort saveExtractPort) {
    this.findCardPort = findCardPort;
    this.updateCardPort = updateCardPort;
    this.saveExtractPort = saveExtractPort;
    this.establishmentFactory = establishmentFactory;
  }

  @Override
  public Extract register(final PurchaseOccurrence purchaseOccurrence) {

    if (!isValidPurchaseOccurrence(purchaseOccurrence)) {
      throw new InvalidPurchaseOccurrenceException();
    }

    var establishment = establishmentFactory.getInstance(purchaseOccurrence.getEstablishmentType());

    var card = findCardPort.find(purchaseOccurrence.getCardNumber());

    if (!establishment.getCardType().equals(card.getType())) {
      throw new IncompatibleCardTypeException();
    }

    var purchaseValue = establishment.calculatePurchaseValue(purchaseOccurrence.getValue());

    var newBalance = card.getBalance() - purchaseValue;

    if (!isValidBalance(newBalance)) {
      throw new InvalidBalanceValueException();
    }

    card.setBalance(newBalance);
    updateCardPort.updateBalance(card);

    var extract = Extract.build(purchaseOccurrence, purchaseValue);

    return saveExtractPort.save(extract);

  }

  private boolean isValidPurchaseOccurrence(final PurchaseOccurrence purchaseOccurrence) {
    return Objects.nonNull(purchaseOccurrence.getValue());
  }

  private boolean isValidBalance(final Double newBalance) {
    return newBalance > 0;
  }

}
