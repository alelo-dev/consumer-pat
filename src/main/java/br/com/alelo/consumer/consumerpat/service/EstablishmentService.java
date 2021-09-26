package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.domain.Establishment;
import br.com.alelo.consumer.consumerpat.dto.EstablishmentDTO;
import br.com.alelo.consumer.consumerpat.enumerator.EstablishmentType;
import br.com.alelo.consumer.consumerpat.mapper.EstablishmentMapper;
import br.com.alelo.consumer.consumerpat.repository.EstablishmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstablishmentService {

    @Autowired
    EstablishmentRepository repository;

    @Autowired
    EstablishmentMapper mapper;

    @Transactional
    public ResponseEntity save(final String cnpj,
                                 final String name,
                                 final EstablishmentType type) {
        try {
            if ( repository.findByCnpj(cnpj).isPresent() ) {
                this.mapper.toDTO(repository.save(this.mapper.toEntity(EstablishmentDTO.builder()
                        .cnpj(cnpj)
                        .type(type)
                        .name(name).build())));
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body(this.mapper.toDTO(repository.save(this.mapper.toEntity(EstablishmentDTO.builder()
                        .cnpj(cnpj)
                        .type(type)
                        .name(name).build()))));
            }
        } catch ( Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    public Page<EstablishmentDTO> findPaginated(final Pageable pageable) {
        Page<Establishment> result = this.repository.findAllPaginated(pageable);
        return new PageImpl<>(this.mapper.toDTOList(result.getContent()), pageable, result.getTotalElements());
    }

}
