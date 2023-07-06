package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
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

    /**
     * Credito de valor no cartão
     * @param cardBalanceRequest
     */
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.PATCH)
    public void setBalance(@RequestBody SetCardBalanceRequest cardBalanceRequest) {
        Card card = repository.findByNumber(cardBalanceRequest.cardNumber);
        if(card != null){
            card.addBalance(cardBalanceRequest.value);
            repository.save(card);
        }
    }

    /*
     *
     *
     * establishmentType: tipo do estabelecimento comercial
     * establishmentName: nome do estabelecimento comercial
     * cardNumber: número do cartão
     * productDescription: descrição do produto
     * value: valor a ser debitado (subtraído)
     */

    /**
     * Débito de valor no cartão (compra)
     *
     * Exemplo: Se a compra é em um estabelecimeto de Alimentação (food) então o valor só pode ser debitado do cartão alimentação
     * Tipos dos estabelcimentos:
     * 1) Alimentação (Food)
     * 2) Farmácia (DrugStore)
     * 3) Posto de combustivel (Fuel)
     *
     * @param buyRequest
     *
     */
    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public void buy(@RequestBody BuyRequest buyRequest) {
        Card card = repository.findByNumber(buyRequest.cardNumber);
        if(card != null && card.getCardType().isEstablishmentAllowed(buyRequest.establishmentType)){
            card.buyingTransaction(buyRequest.value);
            repository.save(card);
            Extract extract = new Extract(buyRequest.establishmentName, buyRequest.productDescription, new Date(), buyRequest.cardNumber, buyRequest.value);
            extractRepository.save(extract);
        }
    }
}
