package br.com.alelo.consumer.consumerpat.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.BuyDto;
import br.com.alelo.consumer.consumerpat.dto.CardDto;
import br.com.alelo.consumer.consumerpat.dto.EstablishmentDto;
import br.com.alelo.consumer.consumerpat.dto.ExtractDto;
import br.com.alelo.consumer.consumerpat.dto.RequestExtractDto;
import br.com.alelo.consumer.consumerpat.dto.TypeCardDto;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.IExtraticService;
import br.com.alelo.consumer.consumerpat.utils.UUIDUtils;

@Service
public class ExtractService implements IExtraticService {

	@Autowired
	ExtractRepository extractRepository;

	@Autowired
	EstablishmentRepository establishmentRepository;

	@Autowired
	CardRepository cardRepository;
	
	@Autowired
	ConsumerRepository consumerRepository;

	@Override
	public ExtractDto saveExtract(BuyDto buyDto) {
		Establishment establishment = establishmentRepository.findById(UUIDUtils.makeUuid(buyDto.getIdEstablishment())).get();
		Card card = cardRepository.findByCardNumber(buyDto.getCardDto().getCardNumber()).get();
		Extract extract = new Extract(establishment, buyDto.getProductDescription(), card, buyDto.getValue());
		extract = extractRepository.save(extract);
		return extractToextractDtoMapper(extract);
	}

	@Override
	public List<ExtractDto> extractAllData(RequestExtractDto requestExtractDto){
		
		Consumer consumer = consumerRepository.findById(UUIDUtils.makeUuid(requestExtractDto.getIdConsumer())).get();
		
		List<Card> collect = consumer.getCards().stream().map(entity -> entity).collect(Collectors.toList());
		List<Extract> allExtracts = new ArrayList<>();
		for (Card card : collect) {
			List<Extract> findByCardIdCard = extractRepository.findByCard(card);
			if(!findByCardIdCard.isEmpty()) {
				allExtracts.addAll(findByCardIdCard);
			}
		}
		
		List<ExtractDto> allExtractsDto = new ArrayList<>();
		for (Extract extract : allExtracts) {
			allExtractsDto.add(extractToextractDtoMapper(extract));
		}
		
		return allExtractsDto;
	}
	
	private ExtractDto extractToextractDtoMapper(Extract extract) {
		ExtractDto extractDto = new ExtractDto();
		CardDto cardDto = new CardDto();
		cardDto.setCardNumber(extract.getCard().getCardNumber());
		TypeCardDto typeCardDto = new TypeCardDto();
		typeCardDto.setIdTypeCard(extract.getCard().getTypeCard().getIdTypeCard());
		typeCardDto.setTypeCard(extract.getCard().getTypeCard().toString());
		cardDto.setTypeCard(typeCardDto);
		extractDto.setCard(cardDto);
		EstablishmentDto establishmentDto = new EstablishmentDto();
		establishmentDto.setIdEstablishment(extract.getStablishment().getIdEstablishment().toString());
		establishmentDto.setNameEstablishment(extract.getStablishment().getNameEstablishment());
		establishmentDto.setTypeEstablishments(extract.getStablishment().getTypeEstablishments());
		extractDto.setStablishment(establishmentDto);
		extractDto.setDateBuy(extract.getDateBuy());
		extractDto.setProductDescription(extract.getProductDescription());
		extractDto.setValue(extract.getValue());
		return extractDto;
	}

}
