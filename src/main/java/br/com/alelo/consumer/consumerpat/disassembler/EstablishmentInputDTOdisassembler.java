package br.com.alelo.consumer.consumerpat.disassembler;

import br.com.alelo.consumer.consumerpat.dto.input.EstablishmentInputDTO;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import org.springframework.stereotype.Component;

@Component
public class EstablishmentInputDTOdisassembler {

    public Establishment toDomainObject (EstablishmentInputDTO establishmentInputDTO){
            Establishment establishment = new Establishment();

            establishment.setEstablishmentType(establishmentInputDTO.getEstablishmentType());
            establishment.setCNPJ(establishmentInputDTO.getCNPJ());
            establishment.setName(establishmentInputDTO.getName());

            return establishment;
    }
}
