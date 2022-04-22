package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.assembler.ConsumerDTOAssembler;
import br.com.alelo.consumer.consumerpat.disassembler.ConsumerInputDTOdisassembler;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.dto.input.ConsumerInputDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.filters.ConsumerFilter;
import br.com.alelo.consumer.consumerpat.respository.specifications.ConsumerSpecifications;
import br.com.alelo.consumer.consumerpat.services.ConsumerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(ConsumerController.PATH)
public class ConsumerController {

    protected static final String PATH = "consumers";

    private final ConsumerService consumerService;
    private final ConsumerDTOAssembler consumerDTOAssembler;
    private final ConsumerInputDTOdisassembler consumerInputDTOdisassembler;

    public ConsumerController(ConsumerService consumerService,
                              ConsumerDTOAssembler consumerDTOAssembler,
                              ConsumerInputDTOdisassembler consumerInputDTOdisassembler) {
        this.consumerService = consumerService;
        this.consumerDTOAssembler = consumerDTOAssembler;
        this.consumerInputDTOdisassembler = consumerInputDTOdisassembler;
    }

    @GetMapping
    public Page<ConsumerDTO> findAll(ConsumerFilter filter,
                                     @PageableDefault(sort = "name", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Consumer> consumersPage = consumerService.findAll(ConsumerSpecifications.filter(filter), pageable);
        List<ConsumerDTO> betsModel = consumerDTOAssembler.toCollectionModel(consumersPage.getContent());
        return new PageImpl<>(betsModel, pageable, consumersPage.getTotalElements());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsumerDTO createConsumer(@RequestBody ConsumerInputDTO consumerInputDTO) {
        Consumer consumer = consumerInputDTOdisassembler.toDomainObject(consumerInputDTO);
        return consumerDTOAssembler.toModel(consumerService.save(consumer));
    }

    @PutMapping("/{id}")
    public ConsumerDTO updateConsumer(@PathVariable Long id,
                                      @RequestBody ConsumerInputDTO consumerInputDTO) {
        Consumer consumer = consumerInputDTOdisassembler.toDomainObject(consumerInputDTO);
        return consumerDTOAssembler.toModel(consumerService.update(id, consumer));

    }

}

