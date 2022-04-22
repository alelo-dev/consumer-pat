package br.com.alelo.consumer.consumerpat.services.impl;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.City;
import br.com.alelo.consumer.consumerpat.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.CityNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.CityRepository;
import br.com.alelo.consumer.consumerpat.services.CityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> findByStateCode(Long id) {
        return cityRepository.findByStateId(id);
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public City findOrFail(Long id){
        return cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id) {
                });
    }
}
