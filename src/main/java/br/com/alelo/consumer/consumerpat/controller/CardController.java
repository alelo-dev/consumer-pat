package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController("/consumer")
public class CardController {

//
//    /*
//     * Deve creditar(adicionar) um valor(value) em um no cartão.
//     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
//     * para isso deve usar o número do cartão(cardNumber) fornecido.
//     */
//    @RequestMapping(value = "/setCardbalance", method = RequestMethod.GET)
//    public void setBalance(int cardNumber, double value) {
//        Consumer consumer = null;
//        consumer = repository.findByDrugstoreNumber(cardNumber);
//
//
//    }

//
//    @ResponseBody
//    @RequestMapping(value = "/buy", method = RequestMethod.GET)
//    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
//        Consumer consumer = null;
//        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
//        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
//        *
//        * Tipos de estabelcimentos
//        * 1 - Alimentação (food)
//        * 2 - Farmácia (DrugStore)
//        * 3 - Posto de combustivel (Fuel)
//        */
//


}
