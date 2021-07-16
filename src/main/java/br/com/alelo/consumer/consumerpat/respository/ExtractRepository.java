package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Extract;

import java.util.List;

public interface ExtractRepository extends BaseRepository<Extract> {

    List<Extract> findAllByCardCardNumber(Long cardNumber);
}
