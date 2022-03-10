package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.entity.Purchase;
import br.com.alelo.consumer.consumerpat.exception.InvalidCardException;
import br.com.alelo.consumer.consumerpat.exception.NoFundsException;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/card")
@AllArgsConstructor
public class CardController {

  private final ExtractService extractService;

  private final CardService cardService;

  @PostMapping("/setCardBalance")
  @ApiOperation("Sets the card balance.")
  public ResponseEntity<Card> setBalance(
      @RequestParam String cardNumber, @RequestParam Double value) {

    return ResponseEntity.ok().body(cardService.setCardBalance(cardNumber, value));
  }

  @ResponseBody
  @PostMapping("/buy")
  @ApiOperation("Makes a purchase.")
  public ResponseEntity<Extract> buy(Purchase purchase)
      throws NoFundsException, InvalidCardException {

    cardService.buy(purchase);

    return ResponseEntity.ok(updateExtract(purchase));
  }

  private Extract updateExtract(Purchase purchase) {
    Extract extract =
        Extract.builder()
            .productDescription(purchase.getProductDescription())
            .establishmentName(purchase.getEstablishmentName())
            .dateBuy(LocalDateTime.now())
            .cardNumber(purchase.getCardNumber())
            .value(purchase.getValue())
            .build();

    extractService.save(extract);
    return extract;
  }
}
