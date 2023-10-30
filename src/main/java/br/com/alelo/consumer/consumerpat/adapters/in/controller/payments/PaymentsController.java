package br.com.alelo.consumer.consumerpat.adapters.in.controller.payments;

import br.com.alelo.consumer.consumerpat.adapters.in.controller.payments.mapper.PaymentMapper;
import br.com.alelo.consumer.consumerpat.adapters.in.controller.payments.request.PaymentRequest;
import br.com.alelo.consumer.consumerpat.application.ports.in.payments.PaymentsInputPort;
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
@RequestMapping("/payments")
public class PaymentsController {

    private final PaymentsInputPort paymentsInputPort;
    private final PaymentMapper paymentMapper;
    @PostMapping
    public ResponseEntity<Void> payment(@Valid @RequestBody PaymentRequest paymentRequest) {
        paymentsInputPort.payment(paymentMapper.toPayment(paymentRequest));

        return ResponseEntity.noContent().build();
    }
}
