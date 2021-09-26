package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.ClientDTO;
import br.com.alelo.consumer.consumerpat.dto.EstablishmentDTO;
import br.com.alelo.consumer.consumerpat.service.EstablishmentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping(EstablishmentController.EST_ENDPOINT)
public class EstablishmentController {

    public static final String EST_ENDPOINT = "/establishment";

    @Autowired
    EstablishmentService service;

    @PostMapping
    EstablishmentDTO create(@RequestBody @Validated EstablishmentDTO dto) {
        return service.save(dto);
    }

    @GetMapping()
    Page<EstablishmentDTO> findAll(@PageableDefault @SortDefault final Pageable pageable) {
        return service.findPaginated(pageable);
    }
}
