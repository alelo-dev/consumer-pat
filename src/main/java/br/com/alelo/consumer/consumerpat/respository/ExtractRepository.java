package br.com.alelo.consumer.consumerpat.respository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.interfaces.IExtractRepository;
import br.com.alelo.consumer.consumerpat.respository.jpa.ExtractJPA;

@Repository
public class ExtractRepository implements IExtractRepository  {
	
	@Autowired
	private ExtractJPA extractJPA;

	@Override
	public void gravarExtract(Extract extract) {

		extractJPA.save(extract);
		
	} 
}


