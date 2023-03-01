package br.com.alelo.consumer.consumerpat.payment.adapter.in;

import br.com.alelo.consumer.consumerpat.payment.application.port.in.NewPayment;
import br.com.alelo.consumer.consumerpat.payment.application.port.in.RegisterPaymentCommand;
import br.com.alelo.consumer.consumerpat.payment.application.port.in.RegisterPaymentUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "Payments", description = "Responsible for payments management.")
@Log4j2
@RestController
@RequestMapping(value = "/payments", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PaymentController {

    private final RegisterPaymentUseCase registerPaymentUseCase;

    @Operation(summary = "Registers card payment.")
    @PostMapping
    public ResponseEntity<Void> registerPayment(@Valid @RequestBody NewPayment newPayment) {
        var command = RegisterPaymentCommand.of(newPayment);
        registerPaymentUseCase.registerPayment(command);
        return ResponseEntity.noContent().build();
    }
}
