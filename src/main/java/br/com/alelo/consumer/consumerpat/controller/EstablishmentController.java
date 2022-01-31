package br.com.alelo.consumer.consumerpat.controller;


import br.com.alelo.consumer.consumerpat.domain.dto.EstablishmentDTO;
import br.com.alelo.consumer.consumerpat.service.EstablishmenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/establishment")
public class EstablishmentController {


    private EstablishmenService service;

    @Autowired
    public EstablishmentController(EstablishmenService service) {
        this.service = service;
    }

    @PostMapping
    @CacheEvict(value = "stablishment_list", allEntries = true)
    public ResponseEntity<EstablishmentDTO> save(@RequestBody EstablishmentDTO establishmentDTO) {
        return ResponseEntity.ok(service.save(establishmentDTO));
    }

    @GetMapping
    @Cacheable("stablishment_list")
    public ResponseEntity<List<EstablishmentDTO>> listaAll() {
        return ResponseEntity.ok(service.getList());
    }

}
