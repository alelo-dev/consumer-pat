package br.com.alelo.consumer.consumerpat.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

/**
 * - Retirado a anotacao @Query e a native query pois o próprio JPA realiza a chamada simples passando somente as variáveis da entidade.
 * @author HLJunior
 *
 */
public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    Consumer findByFoodCardNumber(int foodCardNumber);

    Consumer findByFuelCardNumber(int fuelCardNumber);

    Consumer findByDrugstoreNumber(int drugstoreNumber);
}