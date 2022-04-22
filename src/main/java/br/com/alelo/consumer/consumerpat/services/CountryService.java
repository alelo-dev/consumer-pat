package br.com.alelo.consumer.consumerpat.services;


import br.com.alelo.consumer.consumerpat.entity.Country;
import br.com.alelo.consumer.consumerpat.entity.State;

import java.util.List;

public interface CountryService {

    List<Country> findAll();

    Country findOrFail(Long id);
}
