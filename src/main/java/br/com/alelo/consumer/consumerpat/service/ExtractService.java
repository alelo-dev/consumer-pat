package br.com.alelo.consumer.consumerpat.service;

import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.model.entity.Extract;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExtractService {

	private final ExtractRepository extractRepository;

	public void save(final Extract extract){
		this.extractRepository.saveAndFlush(extract);
	}

}