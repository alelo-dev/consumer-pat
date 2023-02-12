package br.com.alelo.consumer.consumerpat.repository;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.repository.custom.ExtractRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ExtractRepository extends JpaRepository<Extract, Integer>, ExtractRepositoryCustom {

    @Query("select e from Extract e")
    Page<Extract> getAllExtractList(Pageable pageable);

    @Query("select case when count(e) > 0 then true else false end from Extract e " +
            " where e.cardNumber = ?1")
    boolean existsByCardNumber(long cardNumber);

    @Query("select count(e) from Extract e where CAST(e.dateBuy AS date) = CAST(?1 AS date)")
    long getCountExtractDate(LocalDate date);

}