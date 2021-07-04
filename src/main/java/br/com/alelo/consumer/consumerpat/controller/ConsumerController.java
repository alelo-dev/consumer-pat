package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.controller.request.CardRequest;
import br.com.alelo.consumer.consumerpat.controller.request.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.controller.request.EstablishmentRequest;
import br.com.alelo.consumer.consumerpat.controller.response.CardResponse;
import br.com.alelo.consumer.consumerpat.controller.response.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.services.CardService;
import br.com.alelo.consumer.consumerpat.services.ConsumerService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.http.HttpStatus.*;


@Api(tags = "Endpoints de um consumer")
@RestController
@RequestMapping(value = "consumers", produces = "application/json; charset=UTF-8")
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerService consumerService;
    private final CardService cardService;


    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseStatus(code = OK)
    @GetMapping
    public Page<ConsumerResponse> listAllConsumers(@PageableDefault(sort = "consumer_id", direction = ASC) final Pageable pages) {
        return consumerService.getAllConsumersList(pages);
    }

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseStatus(code = OK)
    @GetMapping("{consumer_id}/cards")
    public List<CardResponse> listCardsByConsumer(@PathVariable(name = "consumer_id") final long consumerId) {
        return cardService.findByConsumer(consumerId);
    }


    /* Cadastrar novos clientes */
    @PostMapping
    @ResponseStatus(code = CREATED)
    public ConsumerResponse createConsumer(@RequestBody final ConsumerRequest request) {
        return consumerService.create(request);
    }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping("/{consumer_id}")
    @ResponseStatus(code = NO_CONTENT)
    public void updateConsumer(@PathVariable(name = "consumer_id") final long consumerId, @RequestBody final ConsumerRequest consumer) {
        consumerService.update(consumer, consumerId);
    }
    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @PutMapping(value = "{consumer_id}/cards")
    @ResponseStatus(code = NO_CONTENT)
    public void setBalance(@PathVariable(name = "consumer_id") final long consumerId, @RequestBody CardRequest cardRequest) {
        cardService.updateBalance(consumerId, cardRequest);
    }

    @ResponseBody
    @PostMapping(value = "{consumer_id}/buy")
    public void buy(@PathVariable(name = "consumer_id") final long consumerId, @RequestBody final EstablishmentRequest request) {

        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
        *
        * Tipos de estabelcimentos
        * 1 - Alimentação (food)
        * 2 - Farmácia (DrugStore)
        * 3 - Posto de combustivel (Fuel)
        */
        cardService.registerPurchase(consumerId, request);

    }

}
