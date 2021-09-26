package br.com.alelo.consumer.consumerpat.service.mapper;

import org.springframework.stereotype.Component;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.service.dto.response.ExtractResponse;

@Component
public class ExtractResponseMapper implements EntityMapper<Extract, ExtractResponse>{

	@Override
	public ExtractResponse map(Extract input) {
		if(input == null) {
			return null;
		}
		
		ExtractResponse result = new ExtractResponse();
		result.setId(input.getId());
		result.setDateBuy(input.getDateBuy());
		result.setCardNumber(input.getCardNumber());
		result.setEstablishmentName(input.getEstablishmentName());
		result.setEstablishmentType(input.getEstablishmentType());
		result.setProductDescription(input.getProductDescription());
		result.setValue(input.getValue());
		return result;
	}

}
