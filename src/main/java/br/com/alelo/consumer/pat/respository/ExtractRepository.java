package br.com.alelo.consumer.pat.respository;

import br.com.alelo.consumer.pat.entity.Extract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtractRepository extends JpaRepository<Extract, Long> {

}
