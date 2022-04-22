package br.com.alelo.consumer.consumerpat.services;


import br.com.alelo.consumer.consumerpat.entity.City;

import java.util.List;

public interface CityService {

    List<City> findByStateCode(Long stateCode);

    List<City> findAll();

    City findOrFail(Long id);
}
