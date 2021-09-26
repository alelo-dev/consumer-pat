package br.com.alelo.consumer.consumerpat.service.mapper;

import org.springframework.stereotype.Component;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.service.dto.request.ExtractRequest;

@Component
public class ExtractRequestMapper implements EntityMapper<ExtractRequest, Extract>{

	@Override
	public Extract map(ExtractRequest input) {
		if(input == null) {
			return null;
		}
		
		Extract result = new Extract();
		result.setCardNumber(input.getCardNumber());
		result.setEstablishmentName(input.getEstablishmentName());
		result.setEstablishmentType(input.getEstablishmentType());
		result.setProductDescription(input.getProductDescription());
		result.setValue(input.getValue());
		return result;
	}
}
