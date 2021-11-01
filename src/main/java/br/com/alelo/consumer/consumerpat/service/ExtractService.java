package br.com.alelo.consumer.consumerpat.service;

import java.util.List;

import br.com.alelo.consumer.consumerpat.entity.Extract;

public interface ExtractService {

    List<Extract> list();

    void save(Extract extract);

}
