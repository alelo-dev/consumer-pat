package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.application.ConsumerWrapp;
import br.com.alelo.consumer.consumerpat.controller.response.ConsumerResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.http.HttpStatus.OK;


@Api(tags = "Endpoints de um consumer")
@RestController
@RequestMapping(value = "consumers", produces = "application/json; charset=UTF-8")
@RequiredArgsConstructor
public class ConsumerController {

//    @Autowired
//    ExtractRepository extractRepository;
    private final ConsumerWrapp wrapp;


    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseStatus(code = OK)
    @GetMapping
    public Page<ConsumerResponse> listAllConsumers(@PageableDefault(sort = "id", direction = ASC) final Pageable pages) {
        return wrapp.findAll(pages);
    }


//    /* Cadastrar novos clientes */
//    @PostMapping
//    @ResponseStatus(code = CREATED)
//    public void createConsumer(@RequestBody ConsumerRequest request) {
//        wrapp.create(request);
//    }

//
//    // Não deve ser possível alterar o saldo do cartão
//    @PutMapping
//    public void updateConsumer(@RequestBody Consumer consumer) {
//        repository.save(consumer);
//    }
//
//
//    /*
//     * Deve creditar(adicionar) um valor(value) em um no cartão.
//     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
//     * para isso deve usar o número do cartão(cardNumber) fornecido.
//     */
//    @RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
//    public void setBalance(int cardNumber, double value) {
//        Consumer consumer = null;
//        consumer = repository.findByDrugstoreNumber(cardNumber);
//
//        if(consumer != null) {
//            // é cartão de farmácia
//            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
//            repository.save(consumer);
//        } else {
//            consumer = repository.findByFoodCardNumber(cardNumber);
//            if(consumer != null) {
//                // é cartão de refeição
//                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
//                repository.save(consumer);
//            } else {
//                // É cartão de combustivel
//                consumer = repository.findByFuelCardNumber(cardNumber);
//                consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
//                repository.save(consumer);
//            }
//        }
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/buy", method = RequestMethod.GET)
//    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
//        Consumer consumer = null;
//        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
//        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
//        *
//        * Tipos de estabelcimentos
//        * 1 - Alimentação (food)
//        * 2 - Farmácia (DrugStore)
//        * 3 - Posto de combustivel (Fuel)
//        */
//
//        if (establishmentType == 1) {
//            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
//            Double cashback  = (value / 100) * 10;
//            value = value - cashback;
//
//            consumer = repository.findByFoodCardNumber(cardNumber);
//            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
//            repository.save(consumer);
//
//        }else if(establishmentType == 2) {
//            consumer = repository.findByDrugstoreNumber(cardNumber);
//            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
//            repository.save(consumer);
//
//        } else {
//            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
//            Double tax  = (value / 100) * 35;
//            value = value + tax;
//
//            consumer = repository.findByFuelCardNumber(cardNumber);
//            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
//            repository.save(consumer);
//        }
//
//        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
//        extractRepository.save(extract);
//    }

}
