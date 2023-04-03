package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.request.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.response.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    private final ConsumerService consumerService;

    @GetMapping
    public ResponseEntity<Page<ConsumerResponse>> listAllConsumers(Pageable page) {
        return ResponseEntity.ok(consumerService.findAllConsumers(page));
    }

    @PostMapping
    public ResponseEntity<ConsumerResponse> create(@RequestBody ConsumerRequest consumerRequest) {
        return new ResponseEntity<>(consumerService.save(consumerRequest), HttpStatus.CREATED);
    }

    @PatchMapping(value = "{id}/updateConsumer")
    public ResponseEntity<ConsumerResponse> update(@PathVariable("id") Integer id, @RequestBody ConsumerRequest consumerRequest) {
        return new ResponseEntity<>(consumerService.update(id, consumerRequest), HttpStatus.OK);
    }

}
