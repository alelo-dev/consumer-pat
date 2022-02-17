package br.com.alelo.consumer.consumerpat.entity.establishment;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

public abstract class Establishment {
	
	@Autowired
	protected ConsumerRepository repository;
	
	private static List<Establishment> listEstablishment = Arrays.asList(
			new FoodEstablishment(), 
			new DrugStoreEstablishment(), 
			new FuelEstablishment());
	
	public static Establishment searchEstablishment(int establishmentType) {
		return listEstablishment.get(establishmentType - 1);
	}

	public abstract double discountCalculator(double value);

	public abstract Consumer setCard(int cardNumber, double value);
}
