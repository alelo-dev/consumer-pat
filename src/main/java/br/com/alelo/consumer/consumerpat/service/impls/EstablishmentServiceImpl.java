package br.com.alelo.consumer.consumerpat.service.impls;

import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRespository;
import br.com.alelo.consumer.consumerpat.service.interfaces.EstablishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstablishmentServiceImpl implements EstablishmentService {
    @Autowired
    private EstablishmentRespository establishmentRespository;

    @Override
    public Establishment save(Establishment establishment) {
        return establishmentRespository.save(establishment);
    }
}
