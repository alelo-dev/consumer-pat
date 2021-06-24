package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/consumer")
@Api(value = "Consumer Controller", tags = ConsumerController.TAG_CONSUMER_CONTROLLER)
public class ConsumerController {
	
	static final String TAG_CONSUMER_CONTROLLER = "Consumer Controller";

    @Autowired
    private ConsumerService consumerService;
    
    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "List all Consumers", tags = TAG_CONSUMER_CONTROLLER)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Operation completed successfully.")})
	@ApiImplicitParams({@ApiImplicitParam(name = "page", dataType = "string", paramType = "query", value = "Results page (0..N)"),
						@ApiImplicitParam(name = "size", dataType = "string", paramType = "query", value = "Number of records per page"),
						@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Ordering")})
    public Page<Consumer> listAllConsumers(@PageableDefault(size = 500) Pageable pageable) {
        return consumerService.getAllConsumers(pageable);
    }

    /* Cadastrar novos clientes */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create a new Consumer", tags = TAG_CONSUMER_CONTROLLER, response = Consumer.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Consumer created.")})
    public Consumer createConsumer(@RequestBody Consumer consumer) {
    	return consumerService.createConsumer(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "Update a Consumer", tags = TAG_CONSUMER_CONTROLLER, response = Consumer.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Consumer updated."),
							@ApiResponse(code = 404, message = "Consumer not found.")})
    public Consumer updateConsumer(@RequestBody Consumer consumer) {
    	consumer = consumerService.updateConsumer(consumer);
    	if (consumer == null) {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Consumer Not Found");
    	} else {
    		return consumer;
    	}
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/cardbalance", method = RequestMethod.PUT)
    @ApiOperation(value = "Update Card Balance", tags = TAG_CONSUMER_CONTROLLER, response = Void.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Card Balance updated."),
							@ApiResponse(code = 412, message = "Precondition Failed.")})
    public void setBalance(int cardNumber, double value) {
    	if (!consumerService.setBalance(cardNumber, value)) {
    		throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
    	}
    }

    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    @ApiOperation(value = "Make a purchase", tags = TAG_CONSUMER_CONTROLLER, response = Extract.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successful purchase."),
							@ApiResponse(code = 412, message = "Precondition Failed.")})
    public Extract buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
    	Extract extract = consumerService.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
    	if (extract == null) {
    		throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
    	} else {
    		return extract;
    	}
    }
}
