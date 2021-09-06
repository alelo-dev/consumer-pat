package br.com.alelo.consumer.consumerpat.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {
	
	    List<Consumer> findByDocumentNumber(Integer documentNumber);

//TODO nao necessario, utilizar o findAll()	
//    @Query(nativeQuery = true, value = "select * from Consumer")
//    List<Consumer> getAllConsumersList();

// TODO nao necessario pois foi tipado o cartao.	
//    @Query(nativeQuery = true, value = "select * from Consumer where FOOD_CARD_NUMBER = ? ")
//    Consumer findByFoodCardNumber(int cardNumber);
//
//    @Query(nativeQuery = true, value = "select * from Consumer where FUEL_CARD_NUMBER = ? ")
//    Consumer findByFuelCardNumber(int cardNumber);
//
//    @Query(nativeQuery = true, value = "select * from Consumer where DRUGSTORE_NUMBER = ? ")
//    Consumer findByDrugstoreNumber(int cardNumber);
}
