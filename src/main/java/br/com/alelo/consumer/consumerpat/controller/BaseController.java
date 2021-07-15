package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.BaseEntity;
import br.com.alelo.consumer.consumerpat.service.BaseService;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseController<T extends BaseEntity, S extends BaseService> {

    private final S service;
}
