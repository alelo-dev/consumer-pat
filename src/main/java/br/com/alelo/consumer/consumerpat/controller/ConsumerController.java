package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.application.ConsumerApplicationService;
import br.com.alelo.consumer.consumerpat.domain.model.Consumer;
import br.com.alelo.consumer.consumerpat.enums.EstablishmentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/consumer")
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerApplicationService consumerApplicationService;

    /** Deve listar todos os clientes (cerca de 500) **/
    @GetMapping
    public List<Consumer> listAllConsumers() {
        return consumerApplicationService.listAllConsumers();
    }

    /** Cadastrar novos clientes **/
    @PostMapping
    public void createConsumer(@RequestBody Consumer consumer) {
        consumerApplicationService.createConsumer(consumer);
    }

    /** Não deve ser possível alterar o saldo do cartão **/
    @PutMapping
    public void updateConsumer(@RequestBody Consumer consumer) {
        consumerApplicationService.createConsumer(consumer);
    }

    /**
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     **/
    @PutMapping(value = "/setcardbalance")
    public void setBalance(int cardNumber, double value) {
        consumerApplicationService.setBalance(cardNumber, value);
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
    public void buy(EstablishmentType establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        consumerApplicationService.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
    }

}
