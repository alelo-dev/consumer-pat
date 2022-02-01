package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.BenefitCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BenefitCardRepository extends JpaRepository<BenefitCard, Integer> {
    List<BenefitCard> findAll();
    Optional<BenefitCard> findByCardNumber(int cardNumber);
    Optional<BenefitCard> findById(Long benefit_id);


    //Benefit findBycardNumber(int cardNumber);
    //deleteById(Long benefit_id);

}
