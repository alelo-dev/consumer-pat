package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.BusinessType;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.repository.EstablishmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstablishmentService {

    private final EstablishmentRepository establishmentRepository;

    public Establishment findByTypeAndName(BusinessType type, String establishmentName){
        return establishmentRepository.findByTypeAndName(type.name(), establishmentName)
                                        .orElse(null);
    }
}
