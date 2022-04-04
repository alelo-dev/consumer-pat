package br.com.alelo.consumer.consumerpat.util;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import br.com.alelo.consumer.consumerpat.dto.CardDto;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDto;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;

@Component
public class ObjectConverter {


	public static  Consumer convertConsumerDtoToConsumer( ConsumerDto consumerDto ){
		
		Consumer consumer = new Consumer();
		
		consumer.setName( consumerDto.getName() );
		consumer.setDocumentNumber( consumerDto.getDocumentNumber() );
		consumer.setBirthDate( consumerDto.getBirthDate() );


		consumer.getContact().setMobilePhoneNumber( consumerDto.getContactDto().getMobilePhoneNumber() );
		consumer.getContact().setResidencePhoneNumber( consumerDto.getContactDto().getResidencePhoneNumber() );
		consumer.getContact().setPhoneNumber( consumerDto.getContactDto().getPhoneNumber() );
		consumer.getContact().setEmail( consumerDto.getContactDto().getEmail() );


		consumer.getAddress().setStreet( consumerDto.getAddressDto().getStreet() );
		consumer.getAddress().setNumber(consumerDto.getAddressDto().getNumber() );	
		consumer.getAddress().setCity( consumerDto.getAddressDto().getCity() );
		consumer.getAddress().setCountry( consumerDto.getAddressDto().getCountry() );
		consumer.getAddress().setPostalCode( consumerDto.getAddressDto().getPostalCode() );		

		//-----

		List<Card> listCard = new ArrayList<Card>(); 

		for( CardDto auxCardDto : consumerDto.getCardsDto()  ){

			Card auxCard = new Card();

			auxCard.setType( auxCardDto.getType() );
			auxCard.setNumber( auxCardDto.getNumber() );
			auxCard.setBalance( auxCardDto.getBalance() );
			auxCard.setConsumer( consumer );
			
			listCard.add( auxCard );

		}

		
		consumer.getCards().addAll( listCard );

		//-----

		return consumer;
	}

}