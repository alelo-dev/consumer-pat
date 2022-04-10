package br.com.alelo.consumer.consumerpat.validations;

import java.util.List;

import br.com.alelo.consumer.consumerpat.dto.BuyDto;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.TypeEstablishment;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.utils.UUIDUtils;

public class ValidationsEstablishment {
	
	public static boolean verifyIsEstablishment(BuyDto buyDto, EstablishmentRepository establishmentRepository ) {
        return establishmentRepository.findById(UUIDUtils.makeUuid(buyDto.getIdEstablishment())).isPresent();
    }
	
	public static boolean verifyIsEstablishmentCompatibleWithCard(BuyDto buyDto, EstablishmentRepository establishmentRepository, CardRepository cardRepository) {
		Establishment establishment = establishmentRepository.findById(UUIDUtils.makeUuid(buyDto.getIdEstablishment())).get();
		Card card = cardRepository.findByCardNumber(buyDto.getCardDto().getCardNumber()).get();
		List<TypeEstablishment> typeEstablishments = establishment.getTypeEstablishments();
		for (TypeEstablishment typeEstablishment : typeEstablishments) {
			if(card.getTypeCard().getTypeCard().equals(typeEstablishment.getTypeEstablishment())) {
				return true;
			}
		}
		return false;
	}
	
}
