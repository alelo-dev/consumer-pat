package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.controller.dto.BueConfirmationDto;
import br.com.alelo.consumer.consumerpat.controller.dto.BueDto;
import br.com.alelo.consumer.consumerpat.controller.dto.CardSaveDto;
import br.com.alelo.consumer.consumerpat.controller.dto.CardUpdateDto;
import br.com.alelo.consumer.consumerpat.facade.CardFacade;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/cards")
@AllArgsConstructor
public class CardController {
    
    private final CardFacade facade;
    
    /**
     * Executa uma compra O valores só podem ser debitados dos cartões com os tipos
     * correspondentes ao tipo do estabelecimento da compra. Exemplo: Se a compra é
     * em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado
     * do cartão e alimentação
     *
     * Tipos de estabelecimentos 1 - Alimentação (food) 2 - Farmácia (Drugstore) 3 -
     * Posto de combustivel (Fuel)
     *
     * @param bue
     * @return confirmação da compra.
     */
    @PostMapping("/bue")
    @ResponseStatus(HttpStatus.OK)
    public BueConfirmationDto bue(@RequestBody final BueDto bue) {
        return facade.bue(bue);
        
    }
    
    /**
     * Criar um Cartão
     *
     * @param card
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCard(@RequestBody final CardSaveDto card) {
        facade.save(card);
    }
    
    /**
     * Atualizar um Cartão
     *
     * @param card
     */
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCard(@RequestBody final CardUpdateDto card) {
        facade.update(card);
    }
    
}
