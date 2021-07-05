package br.com.alelo.consumer.consumerpat.integration.rest.controller;

import br.com.alelo.consumer.consumerpat.domain.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.domain.service.exception.ApiException;
import br.com.alelo.consumer.consumerpat.integration.rest.controller.dto.request.ConsumerRequestPostV1;
import br.com.alelo.consumer.consumerpat.integration.rest.controller.dto.request.ConsumerRequestPutV1;
import br.com.alelo.consumer.consumerpat.integration.rest.controller.dto.response.ConsumerResponseV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ConsumerResponseV1>> findAll(Pageable pageable) {
        return ResponseEntity.ok(ConsumerResponseV1.transformToResponse(consumerService.findAll(pageable)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ConsumerResponseV1> save(@RequestBody ConsumerRequestPostV1 consumerRequestPostV1) throws ApiException {
            return ResponseEntity.status(HttpStatus.CREATED).body(ConsumerResponseV1.transformToResponse(consumerService.save(ConsumerRequestPostV1.transformToConsumer(consumerRequestPostV1))));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<ConsumerResponseV1> update(@RequestBody ConsumerRequestPutV1 consumerRequestPutV1) throws ApiException {
            return ResponseEntity.ok(ConsumerResponseV1.transformToResponse(consumerService.update(ConsumerRequestPutV1.transformToConsumer(consumerRequestPutV1))));
    }

}
