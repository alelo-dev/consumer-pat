package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.*;
import br.com.alelo.consumer.consumerpat.entity.*;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static br.com.alelo.consumer.consumerpat.enums.CardType.*;
import static br.com.alelo.consumer.consumerpat.enums.EstablishmentType.*;

@RestController
@RequestMapping("/consumers")
public class ConsumerController {

    private final ConsumerRepository repository;
    private final CardRepository cardRepository;

    @Autowired
    public ConsumerController(ConsumerRepository repository, CardRepository cardRepository) {
        this.repository = repository;
        this.cardRepository = cardRepository;
    }

    /* Deve listar todos os clientes (cerca de 500) */
    @GetMapping
    public Page<ConsumerOutDTO> listAllConsumers(@PageableDefault(direction = Sort.Direction.ASC,
            page = 0, size = 50) Pageable pageable) {

        Page<Consumer> consumerPage = repository.findAll(pageable);

        return consumerPage.map(consumer -> new ConsumerOutDTO(consumer));
    }

    /**
     * Cadastrar novos clientes
     */
    @PostMapping
    @Transactional
    public ResponseEntity<ConsumerOutDTO> createConsumer(@RequestBody ConsumerInDTO dto) {

        if (dto == null) {
            //TODO: implementar payload
            return ResponseEntity.badRequest().build();
        }

        Consumer consumer = new Consumer(dto);

        repository.save(consumer);

        URI uri = UriComponentsBuilder.newInstance() //
                .path("/consumers/{id}") //
                .buildAndExpand(consumer.getId()).toUri();

        ConsumerOutDTO out = new ConsumerOutDTO(consumer);

        return ResponseEntity.created(uri).body(out);
    }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ConsumerOutDTO> updateConsumer(@PathVariable Long id, @RequestBody ConsumerUpdateDTO dto) {

        ResponseEntity response = null;

        Optional<Consumer> consumer = repository.findById(id);

        if (consumer.isPresent()) {

            //mapstruct could save me here, leider nicht
            if (dto.getName() != null)
                consumer.get().setName(dto.getName());
            if (dto.getDocumentNumber() != null)
                consumer.get().setDocumentNumber(dto.getDocumentNumber());
            if (dto.getBirthDate() != null)
                consumer.get().setBirthDate(dto.getBirthDate());
            if (dto.getEmail() != null)
                consumer.get().setEmail(dto.getEmail());
            if (dto.getPhone() != null)
                consumer.get().setPhone(new Phone(dto.getPhone()));
            if (dto.getAddress() != null)
                consumer.get().setAddress(new Address(dto.getAddress()));

            response = ResponseEntity.ok(new ConsumerOutDTO(consumer.get()));

        } else {
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @PutMapping(value = "{id}/balances")
    @Transactional
    public ResponseEntity<?> setBalance(@PathVariable Long id, @RequestBody BalanceDTO balanceDTO) {

        Optional<Consumer> consumer = repository.findById(id);

        if (consumer.isPresent() && balanceDTO.validateMe()) {

            Optional<Card> card = consumer.get().getCards().stream()//
                    .filter(c -> c.getCardType().name().compareTo(balanceDTO.getCardType()) == 0) //
                    .filter(c -> c.getNumber().compareTo(balanceDTO.getCardNumber()) == 0)
                    .findAny();

            if (card.isPresent()) {
                card.get().addBalance(balanceDTO.getValue());
            }
        }
        return ResponseEntity.ok().build();
    }

    /**
     * O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
     * Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
     * <p>
     * Tipos de estabelcimentos
     * 1 - Alimentação (food)
     * 2 - Farmácia (DrugStore)
     * 3 - Posto de combustivel (Fuel)
     */
    @PostMapping("{id}/buy")
    @Transactional
    public ResponseEntity<?> buy(@PathVariable Long id, @RequestBody List<ProductBuyDTO> order) {

        Optional<Consumer> consumer = repository.findById(id);

        if (consumer.isPresent()) {

            order.stream().forEach(
                    p -> {
                        Optional<Card> cardOptional = consumer.get().getCards().stream()//
                                .filter(c -> c.getNumber().compareTo(p.getCardNumber()) == 0) //
                                .findAny();

                        if (cardOptional.isPresent()) {

                            Card card = cardOptional.get();

                            if ((FOOD_CARD.equals(card.getCardType()) && FOOD.name().compareTo(p.getEstablishmentType()) == 0) || //
                                    (DRUGSTORE_CARD.equals(card.getCardType()) && DRUGSTORE.name().compareTo(p.getEstablishmentType()) == 0) || //
                                    (FUEL_CARD.equals(card.getCardType()) && FUEL.name().compareTo(p.getEstablishmentType()) == 0)) {

                                card.subtractBalance(p.getValue());

                                Extract extract = Extract.builder()
                                        .dateBuy(LocalDateTime.now())
                                        .establishmentName(p.getEstablishmentName())
                                        .productDescription(p.getProductDescription())
                                        .value(p.getValue().multiply(new BigDecimal(p.getAmountBuyed()))) //TODO: sobrecarregar o builder do value
                                        .amount(p.getAmountBuyed())
                                        .build();

                                card.getExtract().add(extract);

                            } else {
                                //TODO: implementar erro de compra negada
                            }
                        }
                    }
            );
        } else {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

}
