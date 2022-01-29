package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExtractService {

    private ExtractRepository repository;


    public Extract save(Extract extract) {

        return repository.save(extract);
    }

    public Page<Extract> getAllExtractsByPage(Pageable pageAble ){
       return this.repository.findAll(pageAble);
    }


}
