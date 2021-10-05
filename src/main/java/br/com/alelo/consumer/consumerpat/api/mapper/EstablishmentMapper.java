package br.com.alelo.consumer.consumerpat.api.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alelo.consumer.consumerpat.domain.dto.EstablishmentDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.Establishment;
import br.com.alelo.consumer.consumerpat.domain.service.EstablishmentService;


@Component
public class EstablishmentMapper {

	@Autowired
	private EstablishmentService establishmentService;
	
	public EstablishmentDTO entityToDto(Establishment establishment) {
		EstablishmentDTO establishmentDTO = new EstablishmentDTO();
		BeanUtils.copyProperties(establishment, establishmentDTO);
		return establishmentDTO;
	}
	
	public Establishment dtoToEntity(Long id, EstablishmentDTO establishmentDTO) {
		Establishment establishment = establishmentService.findById(id);
		
		if(establishmentDTO.getAddress() == null) {
			BeanUtils.copyProperties(establishmentDTO, establishment, "id", "address");
		}
		else {
			BeanUtils.copyProperties(establishmentDTO, establishment, "id");
		}
		
		return establishment;
	}

}
