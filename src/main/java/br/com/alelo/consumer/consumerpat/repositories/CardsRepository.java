package br.com.alelo.consumer.consumerpat.repositories;

import br.com.alelo.consumer.consumerpat.models.Cards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardsRepository extends JpaRepository<Cards, UUID> {
    Cards findByFoodCardNumber(int cardNumber);

    Cards findByFuelCardNumber(int cardNumber);

    Cards findByDrugstoreNumber(int cardNumber);
}
