package br.com.alelo.consumer.consumerpat.services;


import br.com.alelo.consumer.consumerpat.entity.Request;

public interface RequestService {

    Request save(Request request);

    Request findOrFail(Long id);

}
