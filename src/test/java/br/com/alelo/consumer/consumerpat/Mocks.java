package br.com.alelo.consumer.consumerpat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.alelo.consumer.consumerpat.application.card.balance.request.CardBalanceRequest;
import br.com.alelo.consumer.consumerpat.application.card.buy.request.CardBuyRequest;
import br.com.alelo.consumer.consumerpat.application.consumer.create.request.CreateConsumerRequest;
import br.com.alelo.consumer.consumerpat.application.consumer.update.request.UpdateConsumerRequest;
import br.com.alelo.consumer.consumerpat.domain.Card;
import br.com.alelo.consumer.consumerpat.domain.CardType;
import br.com.alelo.consumer.consumerpat.domain.Consumer;
import br.com.alelo.consumer.consumerpat.domain.Extract;

public class Mocks {
	public static Card getCard(CardType type, BigDecimal balance) {
		Card card = new Card();
		card.setNumber(1L);
		card.setBalance(balance);
		card.setNumber(1L);
		card.setType(type);
		return card;
	}

	public static Consumer getConsumer() {
		Consumer consumer = new Consumer();
		consumer.setId(1L);
		consumer.setName("teste");
		consumer.setDocumentNumber("1");
		consumer.setBirthDate(LocalDate.now().minusYears(30));
		consumer.setMobilePhoneNumber("5511981185102");
		consumer.setResidencePhoneNumber("1125684675");
		consumer.setEmail("dgs777@gmail.com");

		consumer.setAddressStreet("Rua das Palmeiras");
		consumer.setAddressNumber("999");
		consumer.setAddressCity("São Paulo");
		consumer.setAddressCountry("Brasil");
		consumer.setAddressPostalCode("01399-001");

		return consumer;
	}

	public static UpdateConsumerRequest getUpdateConsumerRequest() {
		UpdateConsumerRequest consumer = new UpdateConsumerRequest();
		consumer.setId(1L);
		consumer.setName("teste");
		consumer.setDocumentNumber("1");
		consumer.setBirthDate(LocalDate.now().minusYears(30));
		consumer.setMobilePhoneNumber("5511981185102");
		consumer.setResidencePhoneNumber("1125684675");
		consumer.setEmail("dgs777@gmail.com");

		consumer.setAddressStreet("Rua das Palmeiras");
		consumer.setAddressNumber("999");
		consumer.setAddressCity("São Paulo");
		consumer.setAddressCountry("Brasil");
		consumer.setAddressPostalCode("01399-001");

		return consumer;

	}

	public static CreateConsumerRequest getCreateConsumerRequest() {
		CreateConsumerRequest consumer = new CreateConsumerRequest();
		consumer.setName("teste");
		consumer.setDocumentNumber("1");
		consumer.setBirthDate(LocalDate.now().minusYears(30));
		consumer.setMobilePhoneNumber("5511981185102");
		consumer.setResidencePhoneNumber("1125684675");
		consumer.setEmail("dgs777@gmail.com");

		consumer.setAddressStreet("Rua das Palmeiras");
		consumer.setAddressNumber("999");
		consumer.setAddressCity("São Paulo");
		consumer.setAddressCountry("Brasil");
		consumer.setAddressPostalCode("01399-001");
		return consumer;

	}

	public static Extract getExtract() {
		Extract extract = new Extract("test", "test", LocalDateTime.now(), 1L, BigDecimal.ONE);
		extract.setId(1L);
		return extract;
	}

	public static CardBalanceRequest getCardBalanceRequest() {
		CardBalanceRequest card = new CardBalanceRequest();
		card.setCardNumber(3L);
		card.setValue(BigDecimal.TEN);
		return card;
	}

	public static CardBuyRequest getCardBuyRequest() {
		CardBuyRequest request = new CardBuyRequest();
		request.setCardNumber(3L);
		request.setEstablishmentName("test");
		request.setProductDescription("test");
		request.setValue(BigDecimal.TEN);
		return request;
	}
}
