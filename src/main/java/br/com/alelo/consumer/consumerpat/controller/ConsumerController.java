package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.*;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Date;

import static org.springframework.http.HttpStatus.NO_CONTENT;


@RestController
@RequestMapping("/consumer")
@Api("Consumer CardPAT")
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerRepository repository;
    private final ExtractRepository extractRepository;
    private final CreditValueCard creditValueCard;

    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lista de cliente encontrada"),
            @ApiResponse(code = 404, message = "Lista de Cliente não encontrado")
    })
    @ApiOperation("Deve listar todos os clientes (cerca de 500), 20 registro por pgina")
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public Page<Consumer> listAllConsumers(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    @ApiOperation("Cadastrar novos clientes")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cliente salvo com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public void createConsumer(@RequestBody Consumer consumer) {
        repository.save(consumer);
    }


    @ApiOperation("Update Consumer - Não deve ser possível alterar o saldo do cartão")
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
    public void updateConsumer(@RequestBody Consumer consumer) {
        repository.findById(consumer.getId())
                .filter(entry -> Double.compare(entry.getFoodCardBalance(), consumer.getFoodCardBalance())  == 0)
                .map( clienteExistente -> {
                    repository.save(consumer);
                    return clienteExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Não é possivel alterar o saldo do cartão") );
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */

    @ResponseStatus(NO_CONTENT)
    @ApiResponses({
            @ApiResponse(code = 204, message = "Valor adicionado ao cartão selecionado"),
            @ApiResponse(code = 404, message = "Cartão não encontrado")
    })
    @ApiOperation("set card balance - Deve creditar(adicionar) um valor(value) em um no cartão.")
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.PATCH)
    public void setBalance(@RequestParam(name = "cardNumber") int cardNumber, @RequestParam(name = "value") double value) {
        Consumer consumer = creditValueCard.effect(cardNumber, value, new DrugstoreCard(repository));
        try {
            if (consumer == null) {
                consumer = creditValueCard.effect(cardNumber, value, new FoodCard(repository));
                if (consumer == null) {
                    consumer = creditValueCard.effect(cardNumber, value, new FuelCard(repository));
                }
            }

            if(consumer == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado");
            }

        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    @ApiOperation("Buy - Compra por tipo de estabelecimentos")
    public void buy(Buy buy) {
        Consumer consumer = null;
        Double value = 0.0;
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
        *
        * Tipos de estabelcimentos
        * 1 - Alimentação (food)
        * 2 - Farmácia (DrugStore)
        * 3 - Posto de combustivel (Fuel)
        */

        try {
            if (buy.getEstablishmentType() == 1) {
                // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
                Double cashback = (buy.getValue() / 100) * 10;
                value = buy.getValue() - cashback;

                consumer = creditValueCard.effect(buy.getCardNumber(), value, new DrugstoreCard(repository));

            } else if (buy.getEstablishmentType() == 2) {
                value = buy.getValue();
                consumer = creditValueCard.effect(buy.getCardNumber(), value, new FoodCard(repository));

            } else if (buy.getEstablishmentType() == 3) {
                // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
                Double tax = (buy.getValue() / 100) * 35;
                value = value + tax;

                consumer = creditValueCard.effect(buy.getCardNumber(), value, new FuelCard(repository));
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de estabelecimento não cadastrado");
            }

            Extract extract = new Extract(buy.getEstablishmentName(), buy.getProductDescription(), new Date(), buy.getCardNumber(), value);
            extractRepository.save(extract);

        }catch(RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de estabelecimento não cadastrado, compra não cadastrada");
        }
    }

}
