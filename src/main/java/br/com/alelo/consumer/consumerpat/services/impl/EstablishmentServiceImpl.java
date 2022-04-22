package br.com.alelo.consumer.consumerpat.services.impl;

import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.exception.EstablishmentCnpjNumberNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.EstablishmentNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.services.EstablishmentService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstablishmentServiceImpl implements EstablishmentService {

    private final EstablishmentRepository establishmentRepository;

    public EstablishmentServiceImpl(EstablishmentRepository establishmentRepository) {
        this.establishmentRepository = establishmentRepository;
    }


    @Override
    public Establishment findOrFail(Long id) {
        return establishmentRepository.findById(id)
                .orElseThrow(() -> new EstablishmentNotFoundException(id) {
                });
    }

    public Establishment findByCnpj(String number) {
        return establishmentRepository.findEstablishmentByCNPJ(number)
                .orElseThrow(() -> new EstablishmentCnpjNumberNotFoundException(number) {
                });
    }

    @Override
    public Establishment save(Establishment establishment) {

        Optional<Establishment> currentEstablishment = establishmentRepository.findEstablishmentByCNPJ(establishment.getCNPJ());

        if (currentEstablishment.isPresent()) {
            throw new EstablishmentCnpjNumberNotFoundException(establishment.getCNPJ());
        }

        return establishmentRepository.save(establishment);
    }

}
