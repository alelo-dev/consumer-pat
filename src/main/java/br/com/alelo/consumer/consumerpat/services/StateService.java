package br.com.alelo.consumer.consumerpat.services;


import br.com.alelo.consumer.consumerpat.entity.State;

import java.util.List;

public interface StateService {

    List<State> findAll();

    State findOrFail(Long id);
}
