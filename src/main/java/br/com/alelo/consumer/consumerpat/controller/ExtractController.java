package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.assembler.ExtractDTOAssembler;
import br.com.alelo.consumer.consumerpat.dto.EstablishmentDTO;
import br.com.alelo.consumer.consumerpat.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.dto.input.ConsumerNameInputDTO;
import br.com.alelo.consumer.consumerpat.dto.input.EstablishmentCnpjInputDTO;
import br.com.alelo.consumer.consumerpat.dto.input.EstablishmentInputDTO;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.services.ExtractService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(ExtractController.PATH)
public class ExtractController {

    protected static final String PATH = "extracts";

    private final ExtractService extractService;
    private final ExtractDTOAssembler extractDTOAssembler;

    public ExtractController(ExtractService extractService, ExtractDTOAssembler extractDTOAssembler) {
        this.extractService = extractService;
        this.extractDTOAssembler = extractDTOAssembler;
    }


    @GetMapping("/{id}")
    public ExtractDTO findById(@PathVariable Long id) {
        Extract extract = extractService.findOrFail(id);
        return extractDTOAssembler.toModel(extract);
    }

    @GetMapping("/findByConsumer")
    public ExtractDTO findByConsumerName(@RequestBody ConsumerNameInputDTO consumerNameInputDTO) {
        Extract extract = extractService.findByConsumerName(consumerNameInputDTO.getName());
        return extractDTOAssembler.toModel(extract);
    }


}
