package br.com.alelo.consumer.consumerpat.adapters.in.controller.cardcustomer;

import br.com.alelo.consumer.consumerpat.adapters.in.controller.cardcustomer.mapper.CardCustomerMapper;
import br.com.alelo.consumer.consumerpat.adapters.in.controller.cardcustomer.request.CardCustomerRequest;
import br.com.alelo.consumer.consumerpat.adapters.in.controller.cardcustomer.response.CardCustomerResponse;
import br.com.alelo.consumer.consumerpat.application.ports.in.cardcustomer.FindCardCustomerInputPort;
import br.com.alelo.consumer.consumerpat.application.ports.in.cardcustomer.InsertCardCustomerInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/customer/{customerId}/card")
public class CardCustomerController {

    private final InsertCardCustomerInputPort insertCardCustomerInputPort;
    private final FindCardCustomerInputPort findCardCustomerInputPort;
    private final CardCustomerMapper cardCustomerMapper;

    @PostMapping
    public ResponseEntity<Void> createCardCustomer(@PathVariable("customerId") UUID customerId, @Valid @RequestBody CardCustomerRequest cardCustomerRequest) {
        insertCardCustomerInputPort.insert(customerId, cardCustomerMapper.toCardCustomer(cardCustomerRequest));
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Set<CardCustomerResponse>> findAllCardsByCustomerId(@PathVariable("customerId") UUID customerId) {
        var cardCustomer = findCardCustomerInputPort.findCardCustomerById(customerId);
        var response = cardCustomer.stream().map(cardCustomerMapper::toCardCustomerResponse).collect(Collectors.toSet());

        if(!response.isEmpty())
            return ResponseEntity.ok(response);

        return ResponseEntity.notFound().build();
    }
}
