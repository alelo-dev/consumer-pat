package br.com.alelo.consumer.consumerpat.adapter.in;

import br.com.alelo.consumer.consumerpat.adapter.in.web.mapper.CardBalanceUpdateDTOMapper;
import br.com.alelo.consumer.consumerpat.adapter.in.web.mapper.ExtractDTOMapper;
import br.com.alelo.consumer.consumerpat.adapter.in.web.mapper.PurchaseOccurrenceDTOMapper;
import br.com.alelo.consumer.consumerpat.adapter.in.web.model.CardBalanceUpdateDTO;
import br.com.alelo.consumer.consumerpat.adapter.in.web.model.ExtractDTO;
import br.com.alelo.consumer.consumerpat.adapter.in.web.model.PurchaseOccurrenceDTO;
import br.com.alelo.consumer.consumerpat.application.port.in.CreditAddition;
import br.com.alelo.consumer.consumerpat.application.port.in.PurchaseRegister;
import br.com.alelo.consumer.consumerpat.common.Adapter;

@Adapter
public class CardAdapter {

  private final CreditAddition creditCardAddition;
  private final PurchaseRegister purchaseRegister;

  public CardAdapter(final CreditAddition creditCardAddition, final PurchaseRegister purchaseRegister) {
    this.creditCardAddition = creditCardAddition;
    this.purchaseRegister = purchaseRegister;
  }

  public CardBalanceUpdateDTO addCredit(final CardBalanceUpdateDTO cardBalanceUpdateDTO) {

    var card = creditCardAddition.credit(cardBalanceUpdateDTO.getCardNumber(),
        cardBalanceUpdateDTO.getValue());

    return CardBalanceUpdateDTOMapper.mapToDTO(card);

  }

  public ExtractDTO registerPurchase(final PurchaseOccurrenceDTO purchaseOccurrenceDTO) {

    var purchaseOccurrence = PurchaseOccurrenceDTOMapper.mapToDomain(purchaseOccurrenceDTO);
    var extract = purchaseRegister.register(purchaseOccurrence);
    
    return ExtractDTOMapper.mapToDTO(extract);

  }
}
