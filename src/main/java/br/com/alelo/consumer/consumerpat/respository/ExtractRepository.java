package br.com.alelo.consumer.consumerpat.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alelo.consumer.consumerpat.entity.Extract;

public interface ExtractRepository extends JpaRepository<Extract, Integer> {

	@Query(nativeQuery = true, value = "select * from Extract")
    List<Extract> getAllExtractList();
	
}
