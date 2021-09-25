package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Setter(onMethod_ = {@Autowired})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EstablishmentService extends CrudService<Establishment, EstablishmentRepository> {

    @Getter
    EstablishmentRepository repository;

}
