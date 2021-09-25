package br.com.alelo.consumer.consumerpat.respository;

import java.util.Optional;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Customer;
import br.com.alelo.consumer.consumerpat.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByNumberAndProductType(final String number, final ProductType productType);

    Optional<Card> findByNumberAndCustomer(final String number, final Customer customer);

}
