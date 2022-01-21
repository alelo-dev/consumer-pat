package br.com.alelo.consumer.consumerpat;

import java.util.Date;
import java.util.UUID;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

public class ConsumerMock {
	
	public static Consumer getConsumer() {
		final Consumer consumer = new Consumer();
		consumer.setId(123);
		consumer.setName(UUID.randomUUID().toString());
		consumer.setDocumentNumber(123);
		consumer.setBirthDate(new Date());
		consumer.getId();
		consumer.getName();
		consumer.getBirthDate();
		return consumer;
	}

}
