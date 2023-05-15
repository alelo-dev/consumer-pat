package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.resource.DefaultApiResponse;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import lombok.extern.log4j.Log4j2;
import br.com.alelo.consumer.consumerpat.enums.EstablishmentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    private final DefaultApiResponse response;

    @Autowired
	public MainController(DefaultApiResponse response) {
		this.response = response;
	}

    @GetMapping("/")
	@ApiOperation(
		value = "Main resource",
		notes = "Update with necessaries"
	)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Request Success")
	})
	public ResponseEntity<String> main() {
		return response.ok("Success");
	}

	@GetMapping("/param")
	@ApiOperation(
		value = "Resource with param",
		notes = "Enabled and documenting params"
	)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Request Success")
	})
	@ApiImplicitParams({
		@ApiImplicitParam(
			name = "Authorization",
			value = "Authorization token",
			required = true,
			paramType = "header"
		)
	})

    @Autowired
    ConsumerRepository repository;

    @Autowired
    ExtractRepository extractRepository;


    /* Listar todos os clientes (obs.: tabela possui cerca de 50.000 registros) */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public List<Consumer> listAllConsumers() {
        log.info("obtendo todos clientes");
        var consumers = repository.getAllConsumersList();

        return consumers;
    }

    /* Cadastrar novos clientes */
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public void createConsumer(@RequestBody Consumer consumer) {
        repository.save(consumer);
    }

    // Atualizar cliente, lembrando que não deve ser possível alterar o saldo do cartão
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
    public void updateConsumer(@RequestBody Consumer consumer) {
        repository.save(consumer);
    }

    /*
     * Credito de valor no cartão
     *
     * cardNumber: número do cartão
     * value: valor a ser creditado (adicionado ao saldo)
     */
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
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

    /*
     * Débito de valor no cartão (compra)
     *
     * establishmentType: tipo do estabelecimento comercial
     * establishmentName: nome do estabelecimento comercial
     * cardNumber: número do cartão
     * productDescription: descrição do produto
     * value: valor a ser debitado (subtraído)
     */
    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        Consumer consumer = null;
        

        switch (establishmentType) {
            case EstablishmentType.Alimentacao:  
                        // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
                        Double cashback  = (value / 100) * 10;
                        value = value - cashback;
            
                        consumer = repository.findByFoodCardNumber(cardNumber);
                        consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
                        repository.save(consumer);
                    break;
            case EstablishmentType.Farmacia: 
                        consumer = repository.findByDrugstoreNumber(cardNumber);
                        consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
                        repository.save(consumer);
                    break;
            case EstablishmentType.Posto_combustivel: 
                        // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
                        Double tax  = (value / 100) * 35;
                        value = value + tax;
            
                        consumer = repository.findByFuelCardNumber(cardNumber);
                        consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
                        repository.save(consumer);
                    break;
          }

        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
        extractRepository.save(extract);
    }

}
