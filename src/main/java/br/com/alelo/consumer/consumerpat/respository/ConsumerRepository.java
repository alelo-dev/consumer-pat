package br.com.alelo.consumer.consumerpat.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    List<Consumer> findAll();
    Consumer findByFoodCardNumber(int foodCardNumber);
    Consumer findByFuelCardNumber(int fuelCardNumber);
    Consumer findByDrugstoreNumber(int drugstoreNumber);
    
}
