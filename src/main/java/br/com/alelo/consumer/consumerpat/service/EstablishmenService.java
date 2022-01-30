package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.dto.EstablishmentDTO;
import br.com.alelo.consumer.consumerpat.entity.mapper.EstablishmentMapper;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.time.LocalDateTime;

@Service
public class EstablishmenService implements Serializable {

    private final EstablishmentRepository repository;

    @Autowired
    public EstablishmenService(EstablishmentRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public EstablishmentDTO save(EstablishmentDTO establishmentDTO){
        var entity = EstablishmentMapper.dtoToEntity(establishmentDTO);
        entity.setCreateDate(LocalDateTime.now());
        entity = this.repository.save(entity);
        return EstablishmentMapper.entityToDTO(entity);
    }

    public Establishment findById(Integer establishmenId) {
        return this.repository.findById(establishmenId).orElseThrow(EntityNotFoundException::new);
    }

    public Page<Establishment> getList(Pageable pageable) {
        return repository.findAll(pageable);
    }

}
