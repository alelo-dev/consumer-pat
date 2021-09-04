package br.com.alelo.consumer.pat.respository;

import br.com.alelo.consumer.pat.entity.Extract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtractRepository extends JpaRepository<Extract, Integer> {
}
