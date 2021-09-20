package br.com.alelo.consumer.consumerpat.respository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

	@Query(nativeQuery = true, value = "select * from Consumer")
	Page<Consumer> getAllConsumersList(Pageable paginacao);

	@Query(nativeQuery = true, value = "select * from Consumer where FOOD_CARD_NUMBER = ? ")
	Consumer findByFoodCardNumber(int cardNumber);

	@Query(nativeQuery = true, value = "select * from Consumer where FUEL_CARD_NUMBER = ? ")
	Consumer findByFuelCardNumber(int cardNumber);

	@Query(nativeQuery = true, value = "select * from Consumer where DRUGSTORE_NUMBER = ? ")
	Consumer findByDrugstoreNumber(int cardNumber);
	
	@Query(nativeQuery = true, value = "select * from Consumer where ID = ? ")
	Consumer consultarPorId(int id);
	
}
