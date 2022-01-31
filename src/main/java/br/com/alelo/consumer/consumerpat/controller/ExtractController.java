package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.domain.entity.Extract;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/extract")
class ExtractController {

    private final ExtractService service;

    @Autowired
    public ExtractController(ExtractService service) {
        this.service = service;
    }

    /* Deve listar todos os Extratos | Inclui a listagem paginada também */
    @GetMapping("/list")
    public ResponseEntity<Page<Extract>> listAllConsumers(@RequestParam("page") int page,
                                                           @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.getAllExtractsByPage(pageable));
    }


    /* Deve listar todos os Extratos */
    @GetMapping
    public ResponseEntity<List<Extract>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

}
