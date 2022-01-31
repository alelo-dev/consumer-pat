package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.domain.entity.Extract;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/consumer")
public class ExtractController {

    private final ExtractService service;

    @Autowired
    public ExtractController(ExtractService service) {
        this.service = service;
    }

    /* Deve listar todos os Extratos */
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/extract-list")
    public ResponseEntity<Page<Extract>> listAllConsumers(@RequestParam("page") int page,
                                                           @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(service.getAllExtractsByPage(pageable));
    }

}
