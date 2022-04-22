package br.com.alelo.consumer.consumerpat.assembler;

import br.com.alelo.consumer.consumerpat.dto.EstablishmentDTO;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstablishmentDTOAssembler {

    public EstablishmentDTO toModel(Establishment establishment) {
        EstablishmentDTO establishmentDTO = new EstablishmentDTO();
        establishmentDTO.setId(establishment.getId());
        establishmentDTO.setEstablishmentType(establishment.getEstablishmentType());
        establishmentDTO.setName(establishment.getName());
        establishmentDTO.setCNPJ(establishment.getCNPJ());

        return establishmentDTO;

    }

    public List<EstablishmentDTO> toCollectionModel(List<Establishment> establishments) {
        return establishments.stream()
                .map(establishment -> toModel(establishment))
                .collect(Collectors.toList());

    }
}
