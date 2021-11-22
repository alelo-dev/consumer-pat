package br.com.alelo.consumer.consumerpat.respository.interfaces;
 
import java.util.List;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
  
public interface IConsumerRepository {
     
	public List<Consumer> getAllConsumersList();   
	public Consumer findByFoodCardNumber(int cardNumber);   
	public Consumer findByFuelCardNumber(int cardNumber);
	public Consumer findByDrugstoreNumber(int cardNumber);
	public void gravarConsumer(Consumer consumer);
}
