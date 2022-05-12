package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.model.exception.CustomException;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.utils.types.CardAndEstablishmentType;
import br.com.alelo.consumer.consumerpat.validator.EstablishmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.com.alelo.consumer.consumerpat.utils.types.ExceptionsType.ESTABLISHMENT_MISSING_FIELD;
import static br.com.alelo.consumer.consumerpat.utils.types.ExceptionsType.ESTABLISHMENT_NOT_FOUND;
import static java.util.Objects.isNull;

@Service
public class EstablishmentService {

    @Autowired
    MessageService messageService;

    @Autowired
    EstablishmentRepository establishmentRepository;

    @Autowired
    EstablishmentValidator validator;

    public Establishment createEstablishment(final Establishment establishment) {

        validator.accept(establishment);
        return establishmentRepository.save(establishment);
    }

    public Establishment getOrCreate(final Establishment establishment) {

        validator.accept(establishment);
        final Optional<Establishment> persistedEstablishment = findEstablishmentByNameAndType(establishment.getName(),
                establishment.getType());
        if (persistedEstablishment.isEmpty()) {
            return createEstablishment(establishment);
        }

        return persistedEstablishment.get();
    }

    public void updateEstablishment(final Establishment establishment) {

        //forçando saber o ID para se houver algum caso de um estabelecimento tiver 2 cadastros diferentes
        //exemplo um posto com loja de conveniência
        if (isNull(establishment.getId())) {
            throw new CustomException(messageService.get(ESTABLISHMENT_MISSING_FIELD.getMessage(), "id"),
                    HttpStatus.BAD_REQUEST, ESTABLISHMENT_MISSING_FIELD.getCode());
        }

        validator.accept(establishment);
        Optional<Establishment> checkPersistedEstablishment = establishmentRepository.findById(establishment.getId());

        if (checkPersistedEstablishment.isPresent()) {
            final Establishment persistedEstablishment = checkPersistedEstablishment.get();
            establishment.setId(persistedEstablishment.getId());
            //impedindo que o tipo seja mudado, por questão de integridade da informação dos extratos
            establishment.setType(persistedEstablishment.getType());
            establishmentRepository.save(establishment);
        } else {
            throw new CustomException(messageService.get(ESTABLISHMENT_NOT_FOUND.getMessage(), establishment.getId()),
                    HttpStatus.NOT_FOUND, ESTABLISHMENT_NOT_FOUND.getCode());
        }
    }

    public List<Establishment> findAll() {
        return establishmentRepository.findAll();
    }

    public Establishment findById(final Long id) {

        Optional<Establishment> response = establishmentRepository.findById(id);
        if (response.isPresent()) {
            return response.get();
        }

        throw new CustomException(messageService.get(ESTABLISHMENT_NOT_FOUND.getMessage(), "id", id),
                HttpStatus.NOT_FOUND, ESTABLISHMENT_NOT_FOUND.getCode());
    }

    public Optional<Establishment> findEstablishmentByNameAndType(final String name,
                                                                  final CardAndEstablishmentType type) {

        return establishmentRepository.findEstablishmentByNameAndType(name, type.name());
    }
}
