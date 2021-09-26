package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.domain.Client;
import br.com.alelo.consumer.consumerpat.domain.Establishment;
import br.com.alelo.consumer.consumerpat.dto.ClientDTO;
import br.com.alelo.consumer.consumerpat.dto.EstablishmentDTO;
import br.com.alelo.consumer.consumerpat.exception.DuplicatedRegistry;
import br.com.alelo.consumer.consumerpat.mapper.EstablishmentMapper;
import br.com.alelo.consumer.consumerpat.repository.EstablishmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstablishmentService {

    @Autowired
    EstablishmentRepository repository;

    @Autowired
    EstablishmentMapper mapper;

    @Transactional
    public EstablishmentDTO save(final EstablishmentDTO dto) {
        try {
            Establishment entity = this.mapper.toEntity(dto);
            return  this.mapper.toDTO(repository.save(entity));
        } catch ( DataIntegrityViolationException ex) {
            throw new DuplicatedRegistry("client");
        }
    }

    public Page<EstablishmentDTO> findPaginated(final Pageable pageable) {
        Page<Establishment> result = this.repository.findAllPaginated(pageable);
        return new PageImpl<>(this.mapper.toDTOList(result.getContent()), pageable, result.getTotalElements());
    }

}
