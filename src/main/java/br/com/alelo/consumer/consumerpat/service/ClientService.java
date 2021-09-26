package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.domain.Client;
import br.com.alelo.consumer.consumerpat.dto.ClientDTO;
import br.com.alelo.consumer.consumerpat.exception.DuplicatedRegistry;
import br.com.alelo.consumer.consumerpat.mapper.ClientMapper;
import br.com.alelo.consumer.consumerpat.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AddressService addressService;

    @Autowired
    ClientMapper mapper;

    @Transactional
    public ClientDTO saveClient(final ClientDTO dto) {
        try {
            Client entity = this.mapper.toEntity(dto);
            return  this.mapper.toDTO(clientRepository.save(entity));
        } catch ( DataIntegrityViolationException ex) {
            throw new DuplicatedRegistry("client");
        }
    }

    public Page<ClientDTO> findPaginated(final Pageable pageable) {
        Page<Client> result = this.clientRepository.findAllPaginated(pageable);
        return new PageImpl<>(this.mapper.toDTOList(result.getContent()), pageable, result.getTotalElements());
    }
}
