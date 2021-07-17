package br.com.alelo.consumer.consumerpat.repository;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for to manipulate data of extract/statement
 *
 * @author mcrj
 */
public interface IExtractRepository extends JpaRepository<Extract, Long> {
}
