package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.model.entity.Extract;
import br.com.alelo.consumer.consumerpat.web.vo.extract.ExtractFilterVO;
import br.com.alelo.consumer.consumerpat.web.vo.extract.NewExtractFormVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExtractService {

    Page<Extract> findAll(ExtractFilterVO filters, Pageable pageable);

    Extract save(NewExtractFormVO form);

}
