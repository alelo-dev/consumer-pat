package br.com.alelo.consumer.consumerpat.adapters.in.controller.card;

import br.com.alelo.consumer.consumerpat.adapters.in.controller.card.request.RechargeCardRequest;
import br.com.alelo.consumer.consumerpat.application.ports.in.card.RechargeCardInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/card")
public class CardController {

    private final RechargeCardInputPort rechargeCardInputPort;

    @PostMapping("/recharge")
    public ResponseEntity<Void> recharge(@Valid @RequestBody RechargeCardRequest cardRechargeRequest) {
        rechargeCardInputPort.recharge(cardRechargeRequest.getCardNumber(), cardRechargeRequest.getAmount());
        return ResponseEntity.noContent().build();
    }
}
