package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Extract;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExtractRepository extends JpaRepository<Extract, Integer> {
	
	@Query(nativeQuery = true, value = "select * from Extract")
    List<Extract> getAllExtractList();
}
