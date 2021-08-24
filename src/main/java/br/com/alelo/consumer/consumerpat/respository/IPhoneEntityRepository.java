package br.com.alelo.consumer.consumerpat.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.alelo.consumer.consumerpat.entity.PhoneEntity;

public interface IPhoneEntityRepository extends JpaRepository<PhoneEntity, Integer>{
    
}
