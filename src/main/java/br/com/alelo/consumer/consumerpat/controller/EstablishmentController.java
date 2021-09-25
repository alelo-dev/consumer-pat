package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.EstablishmentDto;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.mapper.EstablishmentMapper;
import br.com.alelo.consumer.consumerpat.service.EstablishmentService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/establishment")
@Setter(onMethod_ = {@Autowired})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EstablishmentController extends CrudController<Establishment, EstablishmentDto, EstablishmentService, EstablishmentMapper> {

    @Getter
    EstablishmentService service;

    @Getter
    EstablishmentMapper mapper;

}
