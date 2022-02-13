package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.business.ConsumerBusiness;
import br.com.alelo.consumer.consumerpat.contants.DefaultPagination;
import br.com.alelo.consumer.consumerpat.dto.*;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;


@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerBusiness consumerBusiness;


    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Page<ConsumerResponse> listAllConsumers(Pageable p)  {
        if(Objects.isNull(p))
            p = DefaultPagination.DEFAULT_PAGINATION.getPage();

        Page<Consumer> consumers = consumerBusiness.getAllConsumers(p);

        Page<ConsumerResponse> response = consumers.map(consumer -> ResponseMapper.toConsumer(consumer));

        return response;
    }


    /* Cadastrar novos clientes */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity createConsumer(@RequestBody ConsumerRequest consumer) {
        if(Objects.nonNull(consumer.getId()))
            return ResponseEntity.badRequest().body("Campo ID não permitido para inserir");

        consumerBusiness.saveOrUpdate(RequestMapper.toConsumer(consumer));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity updateConsumer(@RequestBody ConsumerUpdateRequest request) {
        if(Objects.isNull(request.getId()))
            return ResponseEntity.badRequest().body("Campo ID não informado");

        Consumer consumer = consumerBusiness.getById(request.getId());
        consumerBusiness.saveOrUpdate(RequestMapper.mergeConsumer(consumer, request));

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{documentNumber}/card", method = RequestMethod.POST)
    public ResponseEntity addCard(@RequestBody CardRequest cardRequest, @PathVariable("documentNumber") String documentNumber) {
        if (Objects.isNull(documentNumber))
            return ResponseEntity.badRequest().body(documentNumber);

        consumerBusiness.addConsumerCard(documentNumber, cardRequest.getType(), cardRequest.getCardNumber());

        return ResponseEntity.ok().build();
    }
}
