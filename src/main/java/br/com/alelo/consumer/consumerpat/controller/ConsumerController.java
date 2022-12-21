package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.BuyRequestDTO;
import br.com.alelo.consumer.consumerpat.dto.CardRequestDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerRequestDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerResponseDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@Api(tags = "Consumer")
@RestController
@Controller
@RequestMapping("/consumer")
public class ConsumerController {
    
    @Autowired
    private ConsumerService consumerService;
    
    /* Deve listar todos os clientes (cerca de 500) */
    @ApiOperation(value = "List", nickname = "listAllConsumer")
    @GetMapping
    public List<ConsumerResponseDTO> listarTodas() {
        return consumerService.listAll().stream()
                .map(consumer -> ConsumerResponseDTO.convertToConsumerResponseDTO(consumer))
                .collect(Collectors.toList());
    }

    /* Cadastrar novo cliente */
    @ApiOperation(value = "Create", nickname = "createConsumer")
    @PostMapping
    public ResponseEntity<ConsumerResponseDTO> salvar(@Valid @RequestBody ConsumerRequestDTO consumerRequestDTO) {
        Consumer consumerCreated = consumerService.create(consumerRequestDTO.convertToConsumerEntity());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ConsumerResponseDTO.convertToConsumerResponseDTO(consumerCreated));
    }
    
    /* Atualizar cliente */
    @ApiOperation(value = "Update", nickname = "updateConsumer")
    @PutMapping("/{id}")
    public ResponseEntity<ConsumerResponseDTO> save(@PathVariable Integer id, @Valid @RequestBody ConsumerRequestDTO consumerRequestDTO) {
    	Consumer consumerToUpdated = consumerService.update(id, consumerRequestDTO.convertToConsumerEntity());
        return ResponseEntity.ok(ConsumerResponseDTO.convertToConsumerResponseDTO(consumerToUpdated));
    }
    
    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @ApiOperation(value = "Add card balance", nickname = "setcardbalance")
    @PatchMapping("/setcardbalance")
    public ResponseEntity<ConsumerResponseDTO> setBalance(@Valid @RequestBody CardRequestDTO cardRequestDTO) {
    	Consumer consumerToAddBalance = consumerService.updateCardBalance(cardRequestDTO.getTypeCard(), cardRequestDTO.getCardNumber(), 
    			cardRequestDTO.getCardBalanceValue());
        return ResponseEntity.ok(ConsumerResponseDTO.convertToConsumerResponseDTO(consumerToAddBalance));
    }
    
    @ApiOperation(value = "Add card balance", nickname = "buy")
    @PatchMapping("/buy")
    public ResponseEntity<ConsumerResponseDTO> buy(@Valid @RequestBody BuyRequestDTO buyRequestDTO) {
    	Consumer consumerToAddBalance = consumerService.buy(buyRequestDTO.getEstablishmentType(), buyRequestDTO.getEstablishmentName(),
    			buyRequestDTO.getCardNumber(), buyRequestDTO.getProductDescription(), buyRequestDTO.getValue());
        return ResponseEntity.ok(ConsumerResponseDTO.convertToConsumerResponseDTO(consumerToAddBalance));
    }
}
