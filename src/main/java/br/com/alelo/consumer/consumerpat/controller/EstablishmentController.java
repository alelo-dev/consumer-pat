package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.service.EstablishmentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Log4j2
@Controller
@RequestMapping("/establishment")
public class EstablishmentController {

    EstablishmentService establishmentService = new EstablishmentService();

    @Autowired
    private EstablishmentRepository establishmentRepository;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/establishmentlist")
    public List<Establishment> listAllEstablishment()  {
        log.info("obtendo todos estabelecimentos");
        return establishmentRepository.getAllEstablishmentList();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/createestablishment")
    public void createEstablishment(@RequestBody Establishment establishment) {
        log.info("estabelecimento criado");
        establishmentRepository.save(establishment);
    }

}
