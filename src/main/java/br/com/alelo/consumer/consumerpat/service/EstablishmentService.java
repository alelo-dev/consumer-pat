package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EstablishmentService {

    @Autowired
    private EstablishmentRepository establishmentRepository;


    public List<Establishment> establishmentList() {
        return establishmentRepository.getAllEstablishmentList();
    }
}
