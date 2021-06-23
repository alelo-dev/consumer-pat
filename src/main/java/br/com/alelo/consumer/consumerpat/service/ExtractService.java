package br.com.alelo.consumer.consumerpat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.model.converter.ExtractConverter;
import br.com.alelo.consumer.consumerpat.model.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

@Service
public class ExtractService {

	@Autowired
	private ExtractRepository extractRepository;

	public void save(final ExtractDTO dto){
		this.extractRepository.save(ExtractConverter.toEntity(dto));
	}

}