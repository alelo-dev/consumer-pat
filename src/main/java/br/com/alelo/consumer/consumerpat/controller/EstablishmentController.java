package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.assembler.EstablishmentDTOAssembler;
import br.com.alelo.consumer.consumerpat.disassembler.EstablishmentInputDTOdisassembler;
import br.com.alelo.consumer.consumerpat.dto.EstablishmentDTO;
import br.com.alelo.consumer.consumerpat.dto.input.EstablishmentCnpjInputDTO;
import br.com.alelo.consumer.consumerpat.dto.input.EstablishmentInputDTO;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.services.EstablishmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(EstablishmentController.PATH)
public class EstablishmentController {

    protected static final String PATH = "establishments";

    private final EstablishmentService establishmentService;
    private final EstablishmentDTOAssembler establishmentDTOAssembler;
    private final EstablishmentInputDTOdisassembler establishmentInputDTOdisassembler;

    public EstablishmentController(EstablishmentService establishmentService,
                                   EstablishmentDTOAssembler establishmentDTOAssembler,
                                   EstablishmentInputDTOdisassembler establishmentInputDTOdisassembler) {
        this.establishmentService = establishmentService;
        this.establishmentDTOAssembler = establishmentDTOAssembler;
        this.establishmentInputDTOdisassembler = establishmentInputDTOdisassembler;
    }


    @GetMapping("/{id}")
    public EstablishmentDTO findById(@PathVariable Long id) {
        Establishment establishment = establishmentService.findOrFail(id);
        return establishmentDTOAssembler.toModel(establishment);
    }

    @GetMapping("/findByCnpj")
    public EstablishmentDTO findEstablishmentByCnpj(@RequestBody EstablishmentCnpjInputDTO establishmentCnpjInputDTO) {
        Establishment establishment = establishmentService.findByCnpj(establishmentCnpjInputDTO.getCNPJ());
        return establishmentDTOAssembler.toModel(establishment);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstablishmentDTO save(@RequestBody EstablishmentInputDTO establishmentInputDTO) {
        Establishment establishment = establishmentInputDTOdisassembler.toDomainObject(establishmentInputDTO);
        return establishmentDTOAssembler.toModel(establishmentService.save(establishment));
    }

}
