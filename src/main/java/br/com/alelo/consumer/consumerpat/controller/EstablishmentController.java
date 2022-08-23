package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.dto.EstablishmentDto;
import br.com.alelo.consumer.consumerpat.service.EstablishmentService;

@RestController
@RequestMapping("/establishments")
public class EstablishmentController {

	@Autowired
	private EstablishmentService establishmentService;
	
    @PostMapping
    public ResponseEntity<Void> createConsumer(@RequestBody EstablishmentDto establishmentDto) {
    	establishmentService.save(establishmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
