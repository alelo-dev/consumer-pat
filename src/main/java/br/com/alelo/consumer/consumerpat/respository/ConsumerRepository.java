package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    @Query(nativeQuery = true, value = "select * from Consumer")
    List<Consumer> getAllConsumersList();

    @Query(nativeQuery = true, value = "select * from Consumer where ID = ? and FOOD_CARD_NUMBER = ? ")
    Consumer findByFoodCardNumber(Integer id, int cardNumber);

    @Query(nativeQuery = true, value = "select * from Consumer where ID = ? and FUEL_CARD_NUMBER = ? ")
    Consumer findByFuelCardNumber(Integer id, int cardNumber);

    // TODO: Do jeito que esta pode vir mais de um registro se duas pessoas tiver o mesmo numero de cartao.
    @Query(nativeQuery = true, value = "select * from Consumer where ID = ? and DRUGSTORE_NUMBER = ?  ")
    Consumer findByDrugstoreNumber(Integer id, int cardNumber);
    
    
}
