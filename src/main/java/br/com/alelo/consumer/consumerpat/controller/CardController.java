package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.objectvalue.CardType;
import br.com.alelo.consumer.consumerpat.objectvalue.EstablishmentType;
import br.com.alelo.consumer.consumerpat.requests.BuyRequest;
import br.com.alelo.consumer.consumerpat.requests.SetCardBalanceRequest;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Log4j2
@Controller
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardRepository repository;

    @Autowired
    ExtractRepository extractRepository;

    /*
     * Credito de valor no cartão
     *
     * cardNumber: número do cartão
     * value: valor a ser creditado (adicionado ao saldo)
     */
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.PATCH)
    public void setBalance(@RequestBody SetCardBalanceRequest cardBalanceRequest) {
        Card card = repository.findByNumber(cardBalanceRequest.cardNumber);
        card.setBalance(card.getBalance() + cardBalanceRequest.value);
        repository.save(card);
    }

    /*
     * Débito de valor no cartão (compra)
     *
     * establishmentType: tipo do estabelecimento comercial
     * establishmentName: nome do estabelecimento comercial
     * cardNumber: número do cartão
     * productDescription: descrição do produto
     * value: valor a ser debitado (subtraído)
     */
    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public void buy(@RequestBody BuyRequest buyRequest) {
        Card card = repository.findByNumber(buyRequest.cardNumber);

        /* O valor só podem ser debitado do catão com o tipo correspondente ao tipo do estabelecimento da compra.

         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação (food) então o valor só pode ser debitado do cartão alimentação
         *
         * Tipos dos estabelcimentos:
         *    1) Alimentação (Food)
         *    2) Farmácia (DrugStore)
         *    3) Posto de combustivel (Fuel)
         */

        if (buyRequest.establishmentType == EstablishmentType.FOOD.getValue() && card.getCardType() == CardType.FOOD) {
            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            Double cashback  = (buyRequest.value / 100) * 10;
            buyRequest.value = buyRequest.value - cashback;

            card.setBalance(card.getBalance() - buyRequest.value);
            repository.save(card);
            Extract extract = new Extract(buyRequest.establishmentName, buyRequest.productDescription, new Date(), buyRequest.cardNumber, buyRequest.value);
            extractRepository.save(extract);

        }else if(buyRequest.establishmentType == EstablishmentType.DRUGSTORE.getValue() && card.getCardType() == CardType.DRUGSTORE) {
            card.setBalance(card.getBalance() - buyRequest.value);
            repository.save(card);
            Extract extract = new Extract(buyRequest.establishmentName, buyRequest.productDescription, new Date(), buyRequest.cardNumber, buyRequest.value);
            extractRepository.save(extract);


        } else if(buyRequest.establishmentType == EstablishmentType.FUEL.getValue() && card.getCardType() == CardType.FUEL) {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            Double tax  = (buyRequest.value / 100) * 35;
            buyRequest.value = buyRequest.value + tax;

            card.setBalance(card.getBalance() - buyRequest.value);
            repository.save(card);
            Extract extract = new Extract(buyRequest.establishmentName, buyRequest.productDescription, new Date(), buyRequest.cardNumber, buyRequest.value);
            extractRepository.save(extract);
        }
    }
}
