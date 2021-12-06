package br.com.alelo.consumer.consumerpat.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.alelo.consumer.consumerpat.adapter.in.CardAdapter;
import br.com.alelo.consumer.consumerpat.adapter.in.web.model.CardBalanceUpdateDTO;
import br.com.alelo.consumer.consumerpat.adapter.in.web.model.ExtractDTO;
import br.com.alelo.consumer.consumerpat.adapter.in.web.model.PurchaseOccurrenceDTO;

@RestController
@RequestMapping("/card")
public class CardController {

  private CardAdapter cardAdapter;

  public CardController(final CardAdapter cardAdapter) {
    this.cardAdapter = cardAdapter;
  }

 
  @PutMapping(value = "/addCredit")
  @ResponseBody
  @ResponseStatus(code = HttpStatus.OK)
  public CardBalanceUpdateDTO addCredit(@RequestBody CardBalanceUpdateDTO cardBalanceUpdateDTO) {

    return cardAdapter.addCredit(cardBalanceUpdateDTO);
  }

  @PostMapping(value = "/registerPurchase")
  @ResponseBody
  @ResponseStatus(code = HttpStatus.OK)
  public ExtractDTO registerPurchase(
      @RequestBody PurchaseOccurrenceDTO purchaseOccurrenceDTO) {

    return cardAdapter.registerPurchase(purchaseOccurrenceDTO);
  }
}
