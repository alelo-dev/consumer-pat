package br.com.alelo.consumer.consumerpat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.IExtraticService;

@Service
public class ExtractService implements IExtraticService {
	

    @Autowired
    ExtractRepository extractRepository;


}
