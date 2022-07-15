package br.com.alelo.consumer.consumerpat.model.repository;

import br.com.alelo.consumer.consumerpat.model.entity.Consumer;
import br.com.alelo.consumer.consumerpat.web.vo.consumer.ConsumerFilterVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

    @Query(value = " SELECT c FROM Consumer c " +
        "WHERE 1 = 1 " +
            "AND (:#{#filters.name} IS NULL OR c.name LIKE %:#{#filters.name}%) " +
            "AND (:#{#filters.documentNumber} IS NULL OR c.documentNumber = :#{#filters.documentNumber}) " +
            "AND (:#{#filters.birthDate} IS NULL OR cast(c.birthDate as LocalDate) = :#{#filters.birthDate}) ")
    Page<Consumer> findAllByFilters(@Param("filters") ConsumerFilterVO filters, Pageable pageable);

    @Query("SELECT c FROM Consumer c WHERE c.documentNumber = :documentNumber")
    Optional<Consumer> findByDocumentNumber(String documentNumber);

}
