package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.requests.BuyRequest;
import br.com.alelo.consumer.consumerpat.requests.SetCardBalanceRequest;
import br.com.alelo.consumer.consumerpat.responses.ApiResponse;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
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
    public ResponseEntity setBalance(@RequestBody @Valid SetCardBalanceRequest cardBalanceRequest,
                                     BindingResult bindingResult) {
        ApiResponse response = new ApiResponse();

        Card card = repository.findByNumber(cardBalanceRequest.cardNumber);

        if(card == null){
            bindingResult.addError(new ObjectError("Card","O usuário solicitado para o adição de saldo não existe"));
        }

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                response.addError(error.getDefaultMessage());
            }

            response.setMessage("Erro ao adicionar saldo no cartão");
            response.setData(cardBalanceRequest);
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        }

        card.addBalance(cardBalanceRequest.value);
        repository.save(card);
        response.setMessage("Saldo adicionado no cartão com sucesso!");
        response.setData(card);
        return new ResponseEntity(response, HttpStatus.OK);
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
    public ResponseEntity buy(@RequestBody @Valid BuyRequest buyRequest,
                              BindingResult bindingResult) {
        ApiResponse response = new ApiResponse();
        Card card = repository.findByNumber(buyRequest.cardNumber);
        try {


            if(card == null){
                bindingResult.addError(new ObjectError("Card","O usuário solicitado para o compra não existe"));
            }

            if(card != null && !card.getCardType().isEstablishmentAllowed(buyRequest.establishmentType)){
                bindingResult.addError(new ObjectError("Card","O cartão não pode ser utulizado nesse tipo de estabelecimento"));
            }

            if (bindingResult.hasErrors()) {
                for (ObjectError error : bindingResult.getAllErrors()) {
                    response.addError(error.getDefaultMessage());
                }

                response.setMessage("Erro ao tentar utilizar o cartão em uma compra");
                response.setData(buyRequest);
                return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
            }

            card.buyingTransaction(buyRequest.value);
            repository.save(card);
            Extract extract = new Extract(buyRequest.establishmentName, buyRequest.productDescription, new Date(), buyRequest.cardNumber, buyRequest.value);
            extractRepository.save(extract);
            response.setMessage("Compra efetuada com sucesso!");
            response.setData(buyRequest);
            return new ResponseEntity(response, HttpStatus.OK);

        }catch (Exception e){
            log.error(e.getMessage());
        }

        response.setMessage("Erro ao tentar utilizar o cartão em uma compra");
        response.setData(card);
        return new ResponseEntity(response,HttpStatus.BAD_REQUEST);

    }
}
