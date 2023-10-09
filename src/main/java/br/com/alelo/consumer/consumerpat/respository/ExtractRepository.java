package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExtractRepository extends JpaRepository<Extract, Long> {

    @Query(nativeQuery = true, value = "select * from Extract")
    Extract findByExtract(double amount);
}
