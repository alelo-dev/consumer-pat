package br.com.alelo.consumer.consumerpat.consumer.adapter.out;

import br.com.alelo.consumer.consumerpat.consumer.domain.Consumer;
import br.com.alelo.consumer.consumerpat.consumer.application.port.out.LoadConsumerPort;
import br.com.alelo.consumer.consumerpat.consumer.application.port.out.SaveConsumerPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer>, SaveConsumerPort, LoadConsumerPort {

    @Query(value = "select distinct c from Consumer c " +
            "left join c.addresses " +
            "left join c.cards " +
            "left join c.contacts ",
            countQuery = "select count(c.id) from Consumer c")
    Page<Consumer> findAll(Pageable pageable);
}
