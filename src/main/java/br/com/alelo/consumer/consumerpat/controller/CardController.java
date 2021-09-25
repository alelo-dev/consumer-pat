package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.CardDto;
import br.com.alelo.consumer.consumerpat.dto.RechargeDto;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Customer;
import br.com.alelo.consumer.consumerpat.exception.RestNotFoundException;
import br.com.alelo.consumer.consumerpat.mapper.CardMapper;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.service.CustomerService;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
Acesso pelo customer pois um card precisa de um customer pra existir
 */
@RestController
@RequestMapping("/customer/{customerId}/card")
@Setter(onMethod_ = {@Autowired})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardController {

    CardService service;

    CustomerService customerService;

    CardMapper mapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void insert(final @PathVariable Long customerId, final @RequestBody CardDto cardDto) {
        final Customer customer = customerService.findById(customerId)
                .orElseThrow(() -> new RestNotFoundException("Customer not found"));
        final Card card = mapper.mapDtoToModel(cardDto);
        card.setCustomer(customer);
        service.save(card);
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @PatchMapping(value = "/recharge", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void rechargeCard(final @PathVariable Long customerId, final @RequestBody RechargeDto rechargeDto) {
        final Customer customer = customerService.findById(customerId)
                .orElseThrow(() -> new RestNotFoundException("Customer not found"));
        final Card card = service.findByNumberAndCustomer(rechargeDto.getCardNumber(), customer)
                .orElseThrow(() -> new RestNotFoundException("Card not found"));
        service.recharge(card, rechargeDto.getValue());
    }

}
