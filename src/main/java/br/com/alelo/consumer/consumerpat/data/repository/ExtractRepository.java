package br.com.alelo.consumer.consumerpat.data.repository;

import br.com.alelo.consumer.consumerpat.data.entity.Extract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExtractRepository extends PagingAndSortingRepository<Extract, Integer> {
    Page<Extract> findAllByCardNumber(String cardNumber, Pageable pageable);
}
