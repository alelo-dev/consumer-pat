package br.com.alelo.consumer.consumerpat.dataprovider.jpa.repository;

import br.com.alelo.consumer.consumerpat.dataprovider.jpa.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressJpaRepository extends JpaRepository<AddressEntity, Long> {
}
