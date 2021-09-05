package br.com.alelo.consumer.pat.respository;

import br.com.alelo.consumer.pat.entity.Consumer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

    Page<Consumer> findAll(final Pageable pageable);

}
