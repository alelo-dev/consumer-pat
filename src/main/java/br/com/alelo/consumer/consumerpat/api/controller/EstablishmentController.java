package br.com.alelo.consumer.consumerpat.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.alelo.consumer.consumerpat.api.mapper.EstablishmentMapper;
import br.com.alelo.consumer.consumerpat.domain.dto.EstablishmentDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.Establishment;
import br.com.alelo.consumer.consumerpat.domain.service.EstablishmentService;

import java.util.List;

@RestController
@RequestMapping(value = EstablishmentController.ESTABLISHMENT_ENDPOINT)
public class EstablishmentController {

    public static final String ESTABLISHMENT_ENDPOINT = "/establishments";
	
    @Autowired
    private EstablishmentService establishmentService;
    
    @Autowired
    private EstablishmentMapper mapper;
    
    @GetMapping
    public List<Establishment> readAll() {
    	return establishmentService.findAll();
    }
    
    @GetMapping("/{id}")
    public Establishment readOne(@PathVariable Long id) {
    	return establishmentService.findById(id);
    }

    @PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Establishment establishment) {
    	establishmentService.save(establishment);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody EstablishmentDTO establishmentDTO) {
    	Establishment establishment = mapper.dtoToEntity(id, establishmentDTO);
    	establishmentService.save(establishment);
    }

    @DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
    	establishmentService.delete(id);
	}
    
  

    

}
