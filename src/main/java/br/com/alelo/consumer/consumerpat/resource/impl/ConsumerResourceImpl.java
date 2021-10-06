package br.com.alelo.consumer.consumerpat.resource.impl;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.resource.ConsumerResource;
import br.com.alelo.consumer.consumerpat.services.impl.ConsumerServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/consumers")
public class ConsumerResourceImpl implements ConsumerResource {

    private static final String CONSUMER_CONTROLLER_METODO = "CONSUMER_CONTROLLER ::: Entrou no método";

    @Autowired
    private ConsumerServiceImpl service;

    public ResponseEntity<Consumer> findById(@PathVariable Integer id) {
        log.info(CONSUMER_CONTROLLER_METODO + " findById");
        return ResponseEntity.ok().body(service.findById(id));
    }

    public ResponseEntity<List<Consumer>> findAll() {
        log.info(CONSUMER_CONTROLLER_METODO + " findAll");
        return ResponseEntity.ok().body(service.findAll());
    }

    public ResponseEntity<Consumer> create(@RequestBody Consumer obj) {
        log.info(CONSUMER_CONTROLLER_METODO + " create");
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(service.create(obj).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    public ResponseEntity<Consumer> update(@PathVariable Integer id, @RequestBody Consumer obj) {
        log.info(CONSUMER_CONTROLLER_METODO + " update");
        return ResponseEntity.ok().body(service.update(id, obj));
    }

}