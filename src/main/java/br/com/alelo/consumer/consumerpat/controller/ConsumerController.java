package br.com.alelo.consumer.consumerpat.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * - Alterado as anotacoes @Controller para @RestController, pois inclui o @ResponseBody (evitando a necessidade de repeticao em cada metodo).
 * - Alterado a anotacao @RequestMapping para as anotacoes @GetMapping e @PostMapping pois ja contem os verbos GET e POST
 * - Alterado o metodo getAllConsumersList() para o metodo findAll(), pois e um metodo do proprio spring que contem o crud basico na qual e realizado um select * from da entidade
 * - Inserido anotacao @API para introducao da classe
 * - Inserido anotacao @ApiOperation para detalhar o metodo
 * - Inserido anotacao @Transaction para realizar o commit no banco de dados e caso ocorre erro realiza o roll-back.
 * @author HLJunior
 *
 */

@RestController
@RequestMapping("/consumer")
@Api(value = "Consumer")
public class ConsumerController {

    @Autowired
    ConsumerRepository repository;

    @Autowired
    ExtractRepository extractRepository;


    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/consumerList")
    @ApiOperation(value = "Listar todos os clientes")
    public List<Consumer> listAllConsumers() {
        return repository.findAll();
    }


    /* Cadastrar novos clientes */
    @PostMapping(value = "/createConsumer")
    @ApiOperation(value = "Cadastrar novos clientes")
    @Transactional
    public void createConsumer(@RequestBody Consumer consumer) {
        repository.save(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @PostMapping(value = "/updateConsumer")
    @ApiOperation(value = "Alterar dados dos clientes")
    @Transactional
    public void updateConsumer(@RequestBody Consumer consumer) {
        repository.save(consumer);
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @GetMapping(value = "/setcardbalance")
    @ApiOperation(value = "Consultar o numero de cartao para creditar um novo valor em um cartao")
    @Transactional
    public void setBalance(int cardNumber, double value) {
        Consumer consumer = null;
        consumer = repository.findByDrugstoreNumber(cardNumber);

        if(consumer != null) {
            // é cartão de farmácia
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
            repository.save(consumer);
        } else {
            consumer = repository.findByFoodCardNumber(cardNumber);
            if(consumer != null) {
                // é cartão de refeição
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
                repository.save(consumer);
            } else {
                // É cartão de combustivel
                consumer = repository.findByFuelCardNumber(cardNumber);
                consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
                repository.save(consumer);
            }
        }
    }

    @GetMapping(value = "/buy")
    @ApiOperation(value = "Debitar valor do cartao")
    @Transactional
    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        Consumer consumer = null;
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
        *
        * Tipos de estabelcimentos
        * 1 - Alimentação (food)
        * 2 - Farmácia (DrugStore)
        * 3 - Posto de combustivel (Fuel)
        */

        if (establishmentType == 1) {
            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            Double cashback  = (value / 100) * 10;
            value = value - cashback;

            consumer = repository.findByFoodCardNumber(cardNumber);
            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
            repository.save(consumer);

        }else if(establishmentType == 2) {
            consumer = repository.findByDrugstoreNumber(cardNumber);
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
            repository.save(consumer);

        } else {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            Double tax  = (value / 100) * 35;
            value = value + tax;

            consumer = repository.findByFuelCardNumber(cardNumber);
            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
            repository.save(consumer);
        }

        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
        extractRepository.save(extract);
    }

}
