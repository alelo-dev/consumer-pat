package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.model.request.BuyRequest;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtractService {

    @Autowired
    ExtractRepository extractRepository;

    public void createExtract(BuyRequest request, Establishment establishment, Card card) {

        final Extract extract = new Extract(null, request.getProductDescription(), request.getDateBuy(),
                request.getValue(), establishment, card);

        extractRepository.save(extract);
    }

    public List<Extract> findAll() {
        return extractRepository.findAll();
    }

}
