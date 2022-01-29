package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class EstablishmenService implements Serializable {

    private final EstablishmentRepository repository;

    @Autowired
    public EstablishmenService(EstablishmentRepository repository) {
        this.repository = repository;
    }

    public Establishment findById(Integer establishmenId) {

        return this.repository.findById(establishmenId).get();

    }

}
