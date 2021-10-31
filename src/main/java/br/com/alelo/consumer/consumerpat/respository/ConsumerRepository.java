package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {
	 
	List<Consumer> findAll();
	
	Consumer findByFoodCardNumber(int cardNumber);
	
    Consumer findByFuelCardNumber(int cardNumber);

    Consumer findByDrugstoreNumber(int cardNumber);
}
