package br.com.alelo.consumerpat.dataprovider.repository;

import br.com.alelo.consumerpat.dataprovider.entity.ExtractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtractRepository extends JpaRepository<ExtractEntity, Long> {
}
