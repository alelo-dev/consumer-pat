package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ExtractRepository extends JpaRepository<Extract, Long> {

    @Query(" select e FROM Extract e WHERE e.consumer = :consumer ORDER BY e.id DESC ")
    List<Extract> findLastExtractByConsumer(Consumer consumer);

}
