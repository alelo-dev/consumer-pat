package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.assembler.CardDTOAssembler;
import br.com.alelo.consumer.consumerpat.disassembler.CardInputDTOdisassembler;
import br.com.alelo.consumer.consumerpat.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.dto.input.CardBalanceInputDTO;
import br.com.alelo.consumer.consumerpat.dto.input.CardInputDTO;
import br.com.alelo.consumer.consumerpat.dto.input.CardNumberInputDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.services.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(CardController.PATH)
public class CardController {

    protected static final String PATH = "cards";

    private final CardService cardService;

    private final CardDTOAssembler cardDTOAssembler;
    private final CardInputDTOdisassembler cardInputDTOdisassembler;


    public CardController(CardService cardService, CardDTOAssembler cardDTOAssembler,
                          CardInputDTOdisassembler cardInputDTOdisassembler) {
        this.cardService = cardService;
        this.cardDTOAssembler = cardDTOAssembler;
        this.cardInputDTOdisassembler = cardInputDTOdisassembler;
    }

    @GetMapping("/{id}")
    public CardDTO findById(@PathVariable Long id) {
        Card card = cardService.findOrFail(id);
        return cardDTOAssembler.toModel(card);
    }

    @GetMapping("/findCardByNumber")
    public CardDTO findCardByNumber(@RequestBody CardNumberInputDTO cardNumberInputDTO) {
        Card card = cardService.findByCardNumber(cardNumberInputDTO.getCardNumber());
        return cardDTOAssembler.toModel(card);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardDTO save(@RequestBody CardInputDTO cardInputDTO) {
        Card card = cardInputDTOdisassembler.toDomainObject(cardInputDTO);
        return cardDTOAssembler.toModel(cardService.save(card));
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */

    @PostMapping("/increaseBalance")
    public CardDTO increaseBalance(@RequestBody CardBalanceInputDTO cardBalanceInputDTO) {
        return cardDTOAssembler.toModel(cardService.increaseCardBalance(
                cardBalanceInputDTO.getCardNumber(), cardBalanceInputDTO.getBalance()));

    }

    @PostMapping("/decreaseBalance")
    public CardDTO decreaseBalance(@RequestBody CardBalanceInputDTO cardBalanceInputDTO) {
        return cardDTOAssembler.toModel(cardService.decreaseBalance(
                cardBalanceInputDTO.getCardNumber(), cardBalanceInputDTO.getBalance()));


    }
}
