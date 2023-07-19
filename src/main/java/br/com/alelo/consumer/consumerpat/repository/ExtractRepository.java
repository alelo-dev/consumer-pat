package br.com.alelo.consumer.consumerpat.repository;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExtractRepository extends JpaRepository<Extract, Integer> {
    @Query(nativeQuery = true, value = "select * from Extract")
    List<Extract> getAllExtractList();
}
