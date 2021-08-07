package br.com.alelo.consumer.consumerpat.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.ConsumerCreateDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;


@Service
public interface IConsumerService {

	/**
	 * Método responsável por realizar a consulta paginada de todos os Consumidores.
	 * @param page, size
	 * @return {@link Page} of {@link ConsumerDTO}
	 */
	Page<ConsumerDTO> pageConsumers(Integer page, Integer size);
	
	/**
	  * Método responsável por resgatar um consumidor cadastrado através do ID.
	  * 
	  * @param id
	  */
	 ConsumerDTO findById(Long id);

	/**
	 * Método responsável por realizar o cadastro de um novo consumidor.
	 * 
	 * @param {@link ConsumerCreateDTO}
	 */
	 Consumer create(ConsumerCreateDTO consumerCreateDTO);

	/**
	 * Método responsável por atualizar os dados cadastrais do consumidor.
	 * 
	 * @param {@link ConsumerDTO}
	 */
	 Consumer update(ConsumerDTO consumerDTO);
	 
	
}
