package br.com.alelo.consumerpat.dataprovider.repository;

import br.com.alelo.consumerpat.dataprovider.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}
