package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ConsumerRepository extends PagingAndSortingRepository<Consumer, Integer> {

//    @Query(nativeQuery = true, value = "select * from Consumer")
    Page<Consumer> findAll(Pageable pageable);

//    @Query(nativeQuery = true, value = "select * from Consumer where FOOD_CARD_NUMBER = ? ")
    Consumer findFirstByFoodCardNumber(Long cardNumber);

//    @Query(nativeQuery = true, value = "select * from Consumer where FUEL_CARD_NUMBER = ? ")
    Consumer findFirstByFuelCardNumber(Long cardNumber);

//    @Query(nativeQuery = true, value = "select * from Consumer where DRUGSTORE_NUMBER = ? ")
    Consumer findFirstByDrugstoreNumber(Long cardNumber);

}
