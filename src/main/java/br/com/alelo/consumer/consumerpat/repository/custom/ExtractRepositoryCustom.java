package br.com.alelo.consumer.consumerpat.repository.custom;

import br.com.alelo.consumer.consumerpat.entity.Extract;

import java.time.LocalDate;
import java.util.List;

public interface ExtractRepositoryCustom {

    List<Extract> findTop10TransactionDay(LocalDate date);

}