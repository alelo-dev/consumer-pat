package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.controller.model.BalanceRequest;
import br.com.alelo.consumer.consumerpat.controller.model.BuyRequest;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.usecase.BuyUsecase;
import br.com.alelo.consumer.consumerpat.usecase.GetAllConsumerUsecase;
import br.com.alelo.consumer.consumerpat.usecase.SaveConsumerUsecase;
import br.com.alelo.consumer.consumerpat.usecase.SetBalanceUsecase;
import br.com.alelo.consumer.consumerpat.usecase.UpdateConsumerUsecase;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/consumer")
@AllArgsConstructor
public class ConsumerController {

    private final GetAllConsumerUsecase getAllConsumerUsecase;
    private final SaveConsumerUsecase saveConsumerUsecase;
    private final UpdateConsumerUsecase updateConsumerUsecase;
    private final SetBalanceUsecase setBalanceUsecase;
    private final BuyUsecase buyUsecase;

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumers", method = RequestMethod.GET)
    public ResponseEntity<List<Consumer>> listAllConsumers() {
        List<Consumer> allConsumersList = getAllConsumerUsecase.execute();
        if (allConsumersList == null || allConsumersList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(allConsumersList);
        }
    }


    /* Cadastrar novos clientes */
    @RequestMapping(method = RequestMethod.POST)
    public void createConsumer(@RequestBody Consumer consumer) {
        saveConsumerUsecase.execute(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(method = RequestMethod.PATCH)
    public void updateConsumer(@RequestBody Consumer consumer) {
        updateConsumerUsecase.execute(consumer);
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @RequestMapping(value = "/cardbalance", method = RequestMethod.POST)
    public void setBalance(@RequestBody BalanceRequest balanceRequest) {
        setBalanceUsecase.execute(balanceRequest);
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public ResponseEntity<Object> buy(@RequestBody BuyRequest buyRequest) {
        try {
            buyUsecase.execute(buyRequest);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
