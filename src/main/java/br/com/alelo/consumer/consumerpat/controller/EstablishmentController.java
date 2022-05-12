package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.service.EstablishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/establishment")
public class EstablishmentController {

    @Autowired
    EstablishmentService establishmentService;

    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/listAll")
    public List<Establishment> listAllEstablishments() {
        return establishmentService.findAll();
    }

    @ResponseBody
    @GetMapping(value = "/findById/{id}")
    public Establishment findEstablishment(@PathVariable(value = "id") Long id) {

        return establishmentService.findById(id);
    }

    @PostMapping(value = "/create")
    public void createEstablishment(@RequestBody Establishment establishment) {

        establishmentService.createEstablishment(establishment);
    }

    @PutMapping(value = "/update")
    public void updateEstablishment(@RequestBody Establishment establishment) {

        establishmentService.updateEstablishment(establishment);
    }
}
