package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.assembler.RequestDTOAssembler;
import br.com.alelo.consumer.consumerpat.disassembler.RequestInputDTODisassembler;
import br.com.alelo.consumer.consumerpat.dto.RequestDTO;
import br.com.alelo.consumer.consumerpat.dto.input.RequestInputDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Request;
import br.com.alelo.consumer.consumerpat.services.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(RequestController.PATH)
public class RequestController {

    protected static final String PATH = "requests";

    private final RequestService requestService;
    private final RequestDTOAssembler requestDTOAssembler;
    private final RequestInputDTODisassembler requestInputDTODisassembler;

    public RequestController(RequestService requestService, RequestDTOAssembler requestDTOAssembler,
                             RequestInputDTODisassembler requestInputDTODisassembler) {
        this.requestService = requestService;
        this.requestDTOAssembler = requestDTOAssembler;
        this.requestInputDTODisassembler = requestInputDTODisassembler;
    }

    @GetMapping("/{id}")
    public RequestDTO findById(@PathVariable Long id) {
        Request request = requestService.findOrFail(id);
        return requestDTOAssembler.toModel(request);
    }

    @PostMapping("/buy")
    @ResponseStatus(HttpStatus.CREATED)
    public RequestDTO createRequest(@RequestBody RequestInputDTO requestInputDTO) {
        Request request = requestInputDTODisassembler.toDomainObject(requestInputDTO);
        return requestDTOAssembler.toModel(requestService.save(request));
    }
}
