package br.com.alelo.consumer.consumerpat.service.extract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

@Service
public class ExtractService {

	 @Autowired
	 ExtractRepository extractRepository;
	 
	 /**
	  * Salva Extract.
	  * 
	  * @param extract
	  */
	 public void saveExtract(Extract extract) {
		 extractRepository.save(extract);
	 }
}
