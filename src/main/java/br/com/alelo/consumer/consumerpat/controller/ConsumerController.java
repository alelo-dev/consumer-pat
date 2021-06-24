package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.application.ConsumerApplicationService;
import br.com.alelo.consumer.consumerpat.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/consumer")
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerApplicationService consumerApplicationService;

    /** Deve listar todos os clientes (cerca de 500) **/

   //devido a uma issue no springfox os parametros do pageable aparecem incorretos no swagger
   //os parametros corretos são "page", "size" e "sort" caso queiram testar a paginação
    @GetMapping
    public Page<ConsumerResponseDto> listAllConsumers(Pageable pageable) {
        return consumerApplicationService.listAllConsumers(pageable);
    }

    /** Cadastrar novos clientes **/
    @PostMapping
    public void createConsumer(@RequestBody ConsumerCreationDto consumer) {
        consumerApplicationService.createConsumer(consumer);
    }

    /** Não deve ser possível alterar o saldo do cartão **/
    @PatchMapping
    public void updateConsumer(@RequestBody ConsumerUpdateDto consumer) {
        consumerApplicationService.updateConsumer(consumer);
    }

    /**
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     **/
    @PutMapping(value = "/setcardbalance")
    public void setBalance(@RequestBody SetBalanceRequestDto balance) {
        consumerApplicationService.setBalance(balance);
    }

    /** O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
     *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
     *
     * Tipos de estabelcimentos
     * 1 - Alimentação (food)
     * 2 - Farmácia (DrugStore)
     * 3 - Posto de combustivel (Fuel)
     **/
    @PostMapping(value = "/buy")
    public void buy(@RequestBody BuyItemRequestDto buyItemRequestDto) {
        consumerApplicationService.buy(buyItemRequestDto);
    }

}
