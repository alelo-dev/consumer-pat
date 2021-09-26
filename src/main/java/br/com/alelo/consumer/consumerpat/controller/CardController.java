package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.dto.CardConsumerRequestDTO;
import br.com.alelo.consumer.consumerpat.dto.ChargeRequestDTO;
import br.com.alelo.consumer.consumerpat.service.ICardService;
import br.com.alelo.consumer.consumerpat.service.IConsumerService;

@RestController
@RequestMapping("/consumer")
public class CardController {
	
	
	@Autowired
	IConsumerService consumerService;
	
	@Autowired
	ICardService cardrService;
	
	@PostMapping(value = "{consumerId}/card/add",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addCardConsumer(final @PathVariable Long consumerId, final @RequestBody CardConsumerRequestDTO cardConsumerRequestDTO) {
		
		cardrService.addCardconsumer(consumerService.getConsumer(consumerId), cardConsumerRequestDTO);
    }
	
    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
	@ResponseStatus(code = HttpStatus.OK)
    @PostMapping(value = "{consumerId}/card/charge", produces = MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
    public void chargeCardConsumer( @PathVariable Long consumerId, @RequestBody ChargeRequestDTO chargeDTO) {
        cardrService.chargeCardConsumer(consumerId, chargeDTO);
    }

}
