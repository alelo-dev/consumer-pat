package br.com.alelo.consumer.consumerpat.recharge.adapter.in;

import br.com.alelo.consumer.consumerpat.recharge.application.port.in.RechargeCommand;
import br.com.alelo.consumer.consumerpat.recharge.application.port.in.RechargeUseCase;
import br.com.alelo.consumer.consumerpat.recharge.application.port.in.NewRecharge;
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

@Tag(name = "Recharges", description = "Responsible for recharges management.")
@Log4j2
@RestController
@RequestMapping(value = "/recharges", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RechargeController {

    private final RechargeUseCase rechargeUseCase;

    @Operation(summary = "Recharges card balance.")
    @PostMapping
    public ResponseEntity<Void> rechargeCard(@Valid @RequestBody NewRecharge newRecharge) {
        var command = RechargeCommand
                .of(newRecharge.getCardNumber(), newRecharge.getAmount(), newRecharge.getRechargedAt());
        rechargeUseCase.recharge(command);
        return ResponseEntity.noContent().build();
    }
}
