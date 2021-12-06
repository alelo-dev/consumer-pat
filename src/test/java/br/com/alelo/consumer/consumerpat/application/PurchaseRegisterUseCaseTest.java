package br.com.alelo.consumer.consumerpat.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import br.com.alelo.consumer.consumerpat.application.EstablishmentFactory;
import br.com.alelo.consumer.consumerpat.application.PurchaseRegisterUseCase;
import br.com.alelo.consumer.consumerpat.application.port.out.FindCardPort;
import br.com.alelo.consumer.consumerpat.application.port.out.SaveExtractPort;
import br.com.alelo.consumer.consumerpat.application.port.out.UpdateCardPort;
import br.com.alelo.consumer.consumerpat.domain.Card;
import br.com.alelo.consumer.consumerpat.domain.CardType;
import br.com.alelo.consumer.consumerpat.domain.Extract;
import br.com.alelo.consumer.consumerpat.domain.FoodEstablishment;
import br.com.alelo.consumer.consumerpat.domain.FuelEstablishment;
import br.com.alelo.consumer.consumerpat.domain.PurchaseOccurrence;
import br.com.alelo.consumer.consumerpat.exception.IncompatibleCardTypeException;
import br.com.alelo.consumer.consumerpat.exception.InvalidBalanceValueException;
import br.com.alelo.consumer.consumerpat.exception.InvalidPurchaseOccurrenceException;

public class PurchaseRegisterUseCaseTest {

  @Mock
  private FindCardPort findCardPort;
  @Mock
  private UpdateCardPort updateCardPort;
  @Mock
  private SaveExtractPort saveExtractPort;
  @Mock
  private EstablishmentFactory establishmentFactory;

  private PurchaseRegisterUseCase purchaseRegisterUseCase;

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
    purchaseRegisterUseCase = new PurchaseRegisterUseCase(findCardPort, updateCardPort,
        establishmentFactory, saveExtractPort);
  }

  @Test
  public void registerSuccess() {
    
    var establishment = new FoodEstablishment();
    
    var card = Card.builder().number(123).balance(10.0).type(CardType.FOOD).build();
    
    var extract = Extract.builder().cardNumber(123).establishmentName("establishmentName").build();
    
    var purchaseOccurrence = PurchaseOccurrence.builder().value(10.0).build();
        
    when(establishmentFactory.getInstance(Mockito.any())).thenReturn(establishment);
    when(findCardPort.find(Mockito.any())).thenReturn(card);
    when(updateCardPort.updateBalance(Mockito.any())).thenReturn(card);
    when(saveExtractPort.save(Mockito.any())).thenReturn(extract);
    
    purchaseRegisterUseCase.register(purchaseOccurrence);
    
    Mockito.verify(establishmentFactory, Mockito.atLeastOnce()).getInstance(Mockito.any());
    Mockito.verify(findCardPort, Mockito.atLeastOnce()).find(Mockito.any());
    Mockito.verify(updateCardPort, Mockito.atLeastOnce()).updateBalance(Mockito.any());
    Mockito.verify(saveExtractPort, Mockito.atLeastOnce()).save(Mockito.any());
    assertEquals(card.getBalance(), 1.0);

  }
  
  @Test
  public void invalidPurchaseOccurrence() {
    
    var purchaseOccurrence = PurchaseOccurrence.builder().build();
        
    Assertions.assertThrows(InvalidPurchaseOccurrenceException.class, () -> {
      purchaseRegisterUseCase.register(purchaseOccurrence);
    });
  }
  
  @Test
  public void IncompatibleCardType() {
    
    var purchaseOccurrence = PurchaseOccurrence.builder().value(10.0).build();
    
    var card = Card.builder().number(123).balance(10.0).type(CardType.FOOD).build();
    
    var establishment = new FuelEstablishment();
    
    when(establishmentFactory.getInstance(Mockito.any())).thenReturn(establishment);
    when(findCardPort.find(Mockito.any())).thenReturn(card);
        
    Assertions.assertThrows(IncompatibleCardTypeException.class, () -> {
      purchaseRegisterUseCase.register(purchaseOccurrence);
    });
  }
  
  @Test
  public void InvalidBalanceValue() {
    
    var purchaseOccurrence = PurchaseOccurrence.builder().value(30.0).build();
    
    var card = Card.builder().number(123).balance(10.0).type(CardType.FOOD).build();
    
    var establishment = new FoodEstablishment();
    
    when(establishmentFactory.getInstance(Mockito.any())).thenReturn(establishment);
    when(findCardPort.find(Mockito.any())).thenReturn(card);
        
    Assertions.assertThrows(InvalidBalanceValueException.class, () -> {
      purchaseRegisterUseCase.register(purchaseOccurrence);
    });
  }

}
