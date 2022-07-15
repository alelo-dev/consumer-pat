package br.com.alelo.consumer.consumerpat.model.repository;

import br.com.alelo.consumer.consumerpat.model.entity.Extract;
import br.com.alelo.consumer.consumerpat.web.vo.extract.ExtractFilterVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExtractRepository extends JpaRepository<Extract, Long> {

    @Query(value = " SELECT e FROM Extract e JOIN e.card c " +
            "WHERE 1 = 1 " +
            "AND (:#{#filters.establishmentId} IS NULL OR e.establishmentId = :#{#filters.establishmentId}) " +
            "AND (:#{#filters.establishmentName} IS NULL OR e.establishmentName LIKE %:#{#filters.establishmentName}%) " +
            "AND (:#{#filters.productDescription} IS NULL OR e.productDescription LIKE %:#{#filters.productDescription}%) " +
            "AND (:#{#filters.value} IS NULL OR e.value = :#{#filters.value}) " +
            "AND (:#{#filters.cardNumber} IS NULL OR c.number = :#{#filters.cardNumber}) " +
            "AND (:#{#filters.dateBuyStart} IS NULL OR cast(e.dateBuy as LocalDate) >= :#{#filters.dateBuyStart}) " +
            "AND (:#{#filters.dateBuyEnd} IS NULL OR cast(e.dateBuy as LocalDate) < :#{#filters.dateBuyEnd}) ")
    Page<Extract> findAllByFilters(@Param("filters") ExtractFilterVO filters, Pageable pageable);

}
