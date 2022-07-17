package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.exception.EstablishmentNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.MoreThanOneEstablishmentFoundException;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EstablishmentService {

    @Autowired
    private EstablishmentRepository establishmentRepository;

    public Establishment findEstablishmentByNameAndType(String establishmentName, Integer establishmentType) {

        List<Establishment> establishmentList = establishmentRepository.findByNameAndType(establishmentName, establishmentType);

        if (establishmentNotFound(establishmentList)) {
            throw new EstablishmentNotFoundException(establishmentName);
        }

        if (hasManyEstablishments(establishmentList)) {
            throw new MoreThanOneEstablishmentFoundException(establishmentName);
        }

        return establishmentList.get(0);
    }

    private boolean establishmentNotFound(List<Establishment> establishmentList) {
        return establishmentList == null || establishmentList.isEmpty();
    }

    private boolean hasManyEstablishments(List<Establishment> establishmentList) {
        return establishmentList != null && establishmentList.size() > 1;
    }
}
