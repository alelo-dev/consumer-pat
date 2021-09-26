package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.ClientDTO;
import br.com.alelo.consumer.consumerpat.service.ClientService;
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
@RequestMapping(ClientController.ClIENT_ENDPOINT)
public class ClientController {
    public static final String ClIENT_ENDPOINT = "/client";

    @Autowired
    private ClientService service;

    @PostMapping
    ClientDTO createClient(@RequestBody @Validated ClientDTO dto) {
        return service.saveClient(dto);
    }

    @GetMapping()
    Page<ClientDTO> getClients(@PageableDefault @SortDefault final Pageable pageable) {
        return service.findPaginated(pageable);
    }

}
