package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.model.BalanceModel;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@RequestMapping("/consumer")
public class ConsumerController {
    ConsumerService service;

    ExtractRepository extractRepository;

    /* Review: A implementação do construtor permite que, em um escopo de teste, você possa
        instanciar a classe sem usar um framework para injeção de dependencia
     */
    ConsumerController(ConsumerService service, ExtractRepository extractRepository){
        this.service = service;
        this.extractRepository = extractRepository;
    }

    /* Review: Removendo redudancia dos paths */

    /* Listar todos os clientes (obs.: tabela possui cerca de 50.000 registros) */

    @ResponseBody
    @GetMapping
    public ResponseEntity<Page<Consumer>> getAllConsumers(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "100") Integer itemsPerPage) {
        log.info("obtendo todos clientes");

        Page<Consumer> response = service.getAllConsumers(page, itemsPerPage);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /* Cadastrar novos clientes */
    @ResponseBody
    @PostMapping
    public ResponseEntity<Consumer> createConsumer(@RequestBody Consumer consumer) {
        return new ResponseEntity<>(service.save(consumer), HttpStatus.CREATED);
    }

    // Atualizar cliente, lembrando que não deve ser possível alterar o saldo do cartão

    @ResponseBody
    @PatchMapping
    public ResponseEntity<Consumer> updateConsumer(@RequestBody Consumer consumer) {
        return new ResponseEntity<>(service.update(consumer), HttpStatus.OK);
    }

    /*
     * Credito de valor no cartão
     *
     * cardNumber: número do cartão
     * value: valor a ser creditado (adicionado ao saldo)
     */
    @PatchMapping(value = "card/balance")
    public Consumer addBalance(@RequestBody BalanceModel balance) {
        return service.addValue(balance.getCardNumber(), balance.getValue());
    }

//    /*
//     * Débito de valor no cartão (compra)
//     *
//     * establishmentType: tipo do estabelecimento comercial
//     * establishmentName: nome do estabelecimento comercial
//     * cardNumber: número do cartão
//     * productDescription: descrição do produto
//     * value: valor a ser debitado (subtraído)
//     */
//    @ResponseBody
//    @RequestMapping(value = "/buy", method = RequestMethod.GET)
//    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
//        Consumer consumer = null;
//        /* O valor só podem ser debitado do catão com o tipo correspondente ao tipo do estabelecimento da compra.
//
//        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação (food) então o valor só pode ser debitado do cartão alimentação
//        *
//        * Tipos dos estabelcimentos:
//        *    1) Alimentação (Food)
//        *    2) Farmácia (DrugStore)
//        *    3) Posto de combustivel (Fuel)
//        */
//
//        if (establishmentType == 1) {
//            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
//            Double cashback  = (value / 100) * 10;
//            value = value - cashback;
//
//            consumer = service.findByFoodCardNumber(cardNumber);
//            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
//            service.save(consumer);
//
//        }else if(establishmentType == 2) {
//            consumer = service.findByDrugstoreNumber(cardNumber);
//            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
//            service.save(consumer);
//
//        } else {
//            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
//            Double tax  = (value / 100) * 35;
//            value = value + tax;
//
//            consumer = service.findByFuelCardNumber(cardNumber);
//            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
//            service.save(consumer);
//        }
//
//        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
//        extractRepository.save(extract);
//    }

}
