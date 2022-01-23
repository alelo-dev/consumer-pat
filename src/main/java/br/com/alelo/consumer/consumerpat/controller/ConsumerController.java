package br.com.alelo.consumer.consumerpat.controller;

import static br.com.alelo.consumer.consumerpat.util.Util.isNull;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.ValidacaoException;
import br.com.alelo.consumer.consumerpat.request.BuyRequest;
import br.com.alelo.consumer.consumerpat.request.UpdateBalanceRequest;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import io.swagger.annotations.Api;


@Api(value = "Permite realizar operações sobre o consumer.")
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerService consumerService;

    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public Page<Consumer> listAllConsumers(@PageableDefault(direction = Sort.Direction.ASC,page = 0, size = 50) Pageable pageable) {
        return (Page<Consumer>) consumerService.getAllConsumersList(pageable);
    }

    @PostMapping
    @Transactional
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public void createConsumer(@RequestBody Consumer consumer) {
    	if(isNull(consumer) ) {
			throw new ValidacaoException("Favor informar todos os dados necessários.");
    	}
    	consumerService.registerConsumer(consumer);
    }

    @Transactional
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
    public void updateConsumer(@RequestBody Consumer consumer) {
    	if(isNull(consumer) ) {
			throw new ValidacaoException("Favor informar todos os dados necessários.");
    	}
    	consumerService.updateConsumer(consumer);
    }

    @RequestMapping(value = "/setCardBalance", method = RequestMethod.POST)
    public void setBalance(@RequestBody UpdateBalanceRequest updateBalance) {
    	if(isNull(updateBalance) || isNull(updateBalance.getCardNumber()) || isNull(updateBalance.getValue())) {
			throw new ValidacaoException("Favor informar todos os dados necessários.");
    	}
    	
    	if( updateBalance.getValue() < 0.0) {
    		throw new ValidacaoException("Valor informado é invalido.");
    	}
    	consumerService.updateBalance(updateBalance);
    }

    @ResponseBody
    @RequestMapping(value = "/buy")
    @Transactional
    public void buy(@RequestBody BuyRequest buyRequest) {
    	
    	if(isNull(buyRequest) || isNull(buyRequest.getEstablishmentName()) || isNull(buyRequest.getCardNumber()) || isNull(buyRequest.getProductDescription()) || isNull(buyRequest.getValue())) {
			throw new ValidacaoException("Favor informar todos os dados necessários.");
    	}
    	
    	consumerService.buy(buyRequest);
    }

}
