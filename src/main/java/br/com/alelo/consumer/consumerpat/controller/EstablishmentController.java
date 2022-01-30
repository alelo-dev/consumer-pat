package br.com.alelo.consumer.consumerpat.controller;


import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.dto.EstablishmentDTO;
import br.com.alelo.consumer.consumerpat.service.EstablishmenService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/establishment")
public class EstablishmentController {

    private EstablishmenService service;


    @PostMapping
    public ResponseEntity<EstablishmentDTO> save(@RequestBody EstablishmentDTO establishmentDTO) {
        return ResponseEntity.ok(service.save(establishmentDTO));
    }

    @GetMapping
    public ResponseEntity<Page<Establishment>> listAllConsumers(@RequestParam("page") int page,
                                                                @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.getList(pageable));
    }

}
