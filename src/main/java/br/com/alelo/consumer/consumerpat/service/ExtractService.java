package br.com.alelo.consumer.consumerpat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;

@Service
public class ExtractService {

    @Autowired
	ExtractRepository extractRepository;

    public List<Extract> listAllExtracts() {
		return extractRepository.getAllExtractList();
	}

    public void addRegisterToExtract(Extract extract){
        extractRepository.save(extract);
    }
}
