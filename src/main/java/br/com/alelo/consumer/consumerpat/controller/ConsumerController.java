package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;

//TODO FALTA TESTES UNITARIOS 
//@Controller
//@RequestMapping("/consumer")
@RestController //TODO utilizar o @RestController
@RequestMapping("/v1/consumer") //TODO Como boa pratica, versionar as uris. ex.: /v1/consumer
public class ConsumerController {

// TODO devera ser implementado uma camada entre a controller e a repository, e retirar toda a regra de negocio da controller.	
//    @Autowired
//    ConsumerRepository repository;
//
//    @Autowired
//    ExtractRepository extractRepository;
	
	  @Autowired
	  private ConsumerService consumerService;
	
	 /* Cadastrar novos clientes */
	 @PostMapping//TODO por padronização, a api /v1/consumer sera utilizada em todos os recursos o que ira diferenciar sera seus verbos.
   @ResponseStatus(code = HttpStatus.CREATED) //TODO todo recurso de criacao retornar com 201.
	 public void createConsumer(@RequestBody Consumer consumer) {
		 consumerService.createConsumer(consumer);
	 }
	 
	 /* Não deve ser possível alterar o saldo do cartão */
	 @PutMapping //TODO por padronização, a api /v1/consumer sera utilizada em todos os recursos o que ira diferenciar sera seus verbos.
	 @ResponseStatus(code = HttpStatus.OK) //TODO todo recurso de atualização retornar com 200.
	 public void updateConsumer(@RequestBody Consumer consumer) {
		 consumerService.updateConsumer(consumer);
	 }

		//TODO Fazer paginção
	 /* Deve listar todos os clientes (cerca de 500) */
   @GetMapping //TODO por padronização, a api /v1/consumer sera utilizada em todos os recursos o que ira diferenciar sera seus verbos.
	 @ResponseStatus(code = HttpStatus.OK) //TODO todo recurso de busca retornar com 200.
	 public ResponseEntity<List<Consumer>> listAllConsumers() {
		 return ResponseEntity.ok(consumerService.listAllConsumers());
	 }


//    /* Deve listar todos os clientes (cerca de 500) */
//    @ResponseBody
//    @ResponseStatus(code = HttpStatus.OK)
//    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
//    public List<Consumer> listAllConsumers() {
//        return repository.getAllConsumersList();
//    }


//    /* Cadastrar novos clientes */
//    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
//    public void createConsumer(@RequestBody Consumer consumer) {
//        repository.save(consumer);
//    }

    // Não deve ser possível alterar o saldo do cartão
//    @RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
//    public void updateConsumer(@RequestBody Consumer consumer) {
//        repository.save(consumer);
//    }


//TODO Nao faz parte do dominio de Consumers e sim de Cards. Criar uma Controller, um Service e um Repository com o Dominio Cards.  
    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
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
//   }

   
   //TODO Nao faz parte do dominio de Consumers e sim establishment, Criar uma Controller, um Service e um Repository com o Dominio establishment.
   //TODO Pelo desacoplamento dos dados de establishment, fazer uma busca pelo id do establishment, verificar o tipo do cartao e se for igual fazer a regra conforme abaixo.
   
   
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
