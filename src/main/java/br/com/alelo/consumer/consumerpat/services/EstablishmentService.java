package br.com.alelo.consumer.consumerpat.services;


import br.com.alelo.consumer.consumerpat.entity.Establishment;

public interface EstablishmentService {

    Establishment save(Establishment establishment);

    Establishment findOrFail(Long id);

    Establishment findByCnpj(String number);

}
