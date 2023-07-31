package br.com.alelo.consumer.consumerpat.application.controller.payment;

import br.com.alelo.consumer.consumerpat.application.controller.payment.payload.PaymentRequest;
import br.com.alelo.consumer.consumerpat.domain.payment.service.PaymentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping(value = "/payments", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Void> registerPayment(@Valid @RequestBody PaymentRequest paymentRequest) {
        paymentService.registerPayment(paymentRequest.getPayment());
        return ResponseEntity.noContent().build();
    }
}
