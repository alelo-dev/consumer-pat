package br.com.alelo.consumer.consumerpat.controller.v1;

import br.com.alelo.consumer.consumerpat.domain.dto.Consumer;
import br.com.alelo.consumer.consumerpat.domain.dto.v2.BuyDTO;
import br.com.alelo.consumer.consumerpat.domain.dto.v2.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.ConsumerEntity;
import br.com.alelo.consumer.consumerpat.domain.entity.enums.CardType;
import br.com.alelo.consumer.consumerpat.domain.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.exception.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.exception.EstablishmentInvalidException;
import br.com.alelo.consumer.consumerpat.domain.exception.InsuficientBalanceException;
import br.com.alelo.consumer.consumerpat.domain.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.domain.repository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Essa controller foi mantida com os contratos e verbos para garantir retrocompatibilidade.
 * Correçoes e melhorias foram feitas na versao 2.0 da API
 *
 */
@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private CardService cardService;

    @Autowired
    private ConsumerService consumerService;


    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        return List.of(new Consumer());
    }


    /* Cadastrar novos clientes */
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public void createConsumer(@RequestBody Consumer consumer) {
        ConsumerDTO consumerDTO = new ConsumerDTO(
                consumer.getId(),
                consumer.getName(),
                consumer.getDocumentNumber(),
                consumer.getBirthDate(),
                null,
                null,
                null
        );
        consumerService.saveConsumer(consumerDTO);

    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
    public void updateConsumer(@RequestBody Consumer consumer) throws ConsumerNotFoundException {
        ConsumerDTO consumerDTO = new ConsumerDTO(
                consumer.getId(),
                consumer.getName(),
                consumer.getDocumentNumber(),
                consumer.getBirthDate(),
                null,
                null,
                null
        );
        consumerService.updateConsumer(consumer.getId(), consumerDTO);
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
    public void setBalance(int cardNumber, double value) throws InsuficientBalanceException, CardNotFoundException {
        cardService.setBalance(cardNumber, value);
    }

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) throws InsuficientBalanceException, EstablishmentInvalidException, CardNotFoundException {

        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
        *
        * Tipos de estabelcimentos
        * 1 - Alimentação (food)
        * 2 - Farmácia (DrugStore)
        * 3 - Posto de combustivel (Fuel)
        */

        BuyDTO buyDTO = new BuyDTO(
                cardNumber,
                establishmentName,
                establishmentType,
                productDescription,
                value);

        cardService.buy(buyDTO);

    }

}
