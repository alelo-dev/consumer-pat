package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.config.SwaggerConfig;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Api(tags = {SwaggerConfig.TAG})
@RequestMapping("/consumers")
public class ConsumerController {

    public static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    ConsumerRepository repository;

    @Autowired
    ExtractRepository extractRepository;

    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    @ApiOperation(value = "Deve listar todos os clientes (cerca de 500).")
    public List<Consumer> listAllConsumers() {
        return repository.findAll();
    }

    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    @ApiOperation(value = "Cadastrar novos clientes.")
    public void createConsumer(@RequestBody Consumer consumer) {
        repository.save(consumer);
    }

    @RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
    @ApiOperation(value = "Não deve ser possível alterar o saldo do cartão")
    public void updateConsumer(@RequestBody Consumer consumer) {
        repository.save(consumer);
    }


    /**
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     **/

    @RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
    @ApiOperation(value = "Deve creditar(adicionar) um valor(value) em um no cartão.")
    public void setBalance(Integer cardNumber, BigDecimal value) {
        Consumer consumer = null;
        consumer = repository.findByDrugstoreNumber(cardNumber);

        if (consumer != null) {
            /** é cartão de farmácia */
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance().add(value));
            repository.save(consumer);
        } else {
            consumer = repository.findByFoodCardNumber(cardNumber);
            if (consumer != null) {
                /** é cartão de refeição */
                consumer.setFoodCardBalance(consumer.getFoodCardBalance().add(value));
                repository.save(consumer);
            } else {
                /** É cartão de combustivel */
                consumer = repository.findByFuelCardNumber(cardNumber);
                consumer.setFuelCardBalance(consumer.getFuelCardBalance().add(value));
                repository.save(consumer);
            }
        }
    }

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    @ApiOperation(value = "Os valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do " +
            " estabelecimento da compra.")

    public void buy(int establishmentType, String establishmentName, int cardNumber,
                    String productDescription, BigDecimal value) {
        var consumer = new Consumer();
        /**
         * Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser
         * debitado do cartão e alimentação
         *
         * Tipos de estabelcimentos
         * 1 - Alimentação (food)
         * 2 - Farmácia (DrugStore)
         * 3 - Posto de combustivel (Fuel)
         **/

        if (establishmentType == 1) {
            /** Para compras no cartão de alimentação o cliente recebe um desconto de 10% */
            BigDecimal cashback = value.divide(BigDecimal.valueOf(100L)).multiply(BigDecimal.TEN);
            value = value.subtract(cashback);

            consumer = repository.findByFoodCardNumber(cardNumber);
            if (consumer != null) {
                consumer.setFoodCardBalance(consumer.getFoodCardBalance().subtract(value));
                repository.save(consumer);
            }
        } else if (establishmentType == 2) {
            consumer = repository.findByDrugstoreNumber(cardNumber);
            if (consumer != null) {
                consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance().subtract(value));
                repository.save(consumer);
            }
        } else {
            /** Nas compras com o cartão de combustivel existe um acrescimo de 35%; */

            BigDecimal tax = value.divide(BigDecimal.valueOf(100L).multiply(BigDecimal.valueOf(35L)));
            value = value.add(tax);

            consumer = repository.findByFuelCardNumber(cardNumber);
            consumer.setFuelCardBalance(consumer.getFuelCardBalance().subtract(value));
            repository.save(consumer);
        }

        Extract extract = new Extract(establishmentName, productDescription, LocalDateTime.now(), cardNumber, value);
        extractRepository.save(extract);
        logger.info("Compra efetuada com sucesso - {}",HttpStatus.OK);
    }

}
