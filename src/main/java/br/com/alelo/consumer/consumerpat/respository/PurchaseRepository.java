package br.com.alelo.consumer.consumerpat.respository;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.alelo.consumer.consumerpat.entity.Purchase;

public interface PurchaseRepository  extends JpaRepository<Purchase, Long>  {

}