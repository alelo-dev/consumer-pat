package br.com.alelo.consumer.consumerpat.mock.entity;

import java.time.LocalDate;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

public class ConsumerMock {

	private ConsumerMock() {}
	
	public static Consumer created() {
		return new Consumer(1L, "Joao", 112233, LocalDate.of(1992, 12, 31), AddressMock.listCreate(), CardMock.listCreate(), ContactMock.listCreate());
	}

}
