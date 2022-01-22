package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.controller.validator.CardValidator;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    CardService cardService;

    @Autowired
    ExtractRepository extractRepository;

    /**
     * Deve creditar (adicionar) um valor (value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão (cardNumber) fornecido.
     *
     * @param cardNumber
     * @param value
     */
    @PatchMapping(value = "/setcardbalance")
    public ResponseEntity<String> setBalance(String cardNumber, Double value) {

        final String validated = CardValidator.validateSetBalance(cardNumber, value);
        if (Objects.isNull(validated)) {
            cardService.setBalance(cardNumber, value);
            return ResponseEntity.ok().build();
        } else {
            log.error("Falha validação");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validated);
        }
    }

    /**
     * O valor só pode ser debitado do cartão com os tipo correspondentes ao tipo do estabelecimento da compra.
     *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(FOOD) então o valor só pode ser debitado do cartão e alimentação(FOOD)
     *
     * Tipos de estabelcimentos
     * 1 - Alimentação (food)
     * 2 - Farmácia (DrugStore)
     * 3 - Posto de combustivel (Fuel)
     * @param establishmentType
     * @param establishmentName
     * @param cardNumber
     * @param productDescription
     * @param value
     */
    @PostMapping(value = "/buy")
    public void buy(Integer establishmentType, String establishmentName, String cardNumber, String productDescription, Double value) {
        cardService.buy(establishmentType,establishmentName,cardNumber,productDescription,value);
    }
}
