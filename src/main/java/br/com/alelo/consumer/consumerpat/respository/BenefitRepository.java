package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Benefit;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BenefitRepository  extends JpaRepository<Benefit, Integer> {

    //Benefit setBenefit(Benefit benefit);
    List<Benefit> findAll();
    Benefit findBycardNumber(int cardNumber);
    //deleteById(Long benefit_id);
    Optional<Benefit> findById(Long benefit_id);
//
//    Boolean deleteByCardNumber(int cardNumber);
}
