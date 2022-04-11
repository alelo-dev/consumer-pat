package br.com.alelo.consumer.consumerpat.controllers;

import br.com.alelo.consumer.consumerpat.dtos.AddressDto;
import br.com.alelo.consumer.consumerpat.dtos.CardsDto;
import br.com.alelo.consumer.consumerpat.dtos.ConsumerDto;
import br.com.alelo.consumer.consumerpat.dtos.ContactDto;
import br.com.alelo.consumer.consumerpat.models.Consumer;
import br.com.alelo.consumer.consumerpat.services.ConsumerService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    final ConsumerService consumerService;

    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    /* Cadastrar novos clientes */
    @PostMapping
    public ResponseEntity<Object> createConsumer(@RequestBody ConsumerDto consumerDto) {
        var consumer = new Consumer();
        BeanUtils.copyProperties(consumerDto, consumer);
        return ResponseEntity.status(HttpStatus.CREATED).body(consumerService.save(consumer));
    }

    /* Deve listar todos os clientes (cerca de 500) */
    @GetMapping
    public ResponseEntity<List<Consumer>> listAllConsumers() {
        return ResponseEntity.status(HttpStatus.OK).body(consumerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable(value = "id") UUID id) {
        Optional<Consumer> consumerOptional = consumerService.findById(id);
        if (!consumerOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consumer not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(consumerOptional.get());
    }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateConsumer(@PathVariable(value = "id")UUID id, @RequestBody ConsumerDto consumerDto) {
        Optional<Consumer> consumerOptional = consumerService.findById(id);
        if (!consumerOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consumer not found.");
        }
        var consumer = consumerOptional.get();
        consumer.setName(consumerDto.getName());
        consumer.setBirthDate(consumerDto.getBirthDate());
        consumer.setDocumentNumber(consumerDto.getDocumentNumber());

        changeCardBalance(consumerDto, consumerOptional.get());

        AddressDto addressDto = consumerDto.getAddressDto();
        consumerDto.setAddressDto(addressDto);

        CardsDto cardsDto = consumerDto.getCardsDto();
        consumerDto.setCardsDto(cardsDto);

        ContactDto contactDto = consumerDto.getContactDto();
        consumerDto.setContactDto(contactDto);

        return ResponseEntity.status(HttpStatus.OK).body(consumerService.save(consumer));
    }

    private void changeCardBalance(ConsumerDto consumerDto, Consumer consumer) {
        if (consumerDto.getCardsDto().getDrugstoreCardBalance() != consumer.getCards().getDrugstoreCardBalance()
                || consumerDto.getCardsDto().getFoodCardBalance() != consumer.getCards().getFoodCardBalance()
                || consumerDto.getCardsDto().getFuelCardBalance() != consumer.getCards().getFuelCardBalance()) {
            throw new RuntimeException("You cannot change card balance");
        }
    }

}
