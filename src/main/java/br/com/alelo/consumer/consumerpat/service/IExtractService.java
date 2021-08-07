package br.com.alelo.consumer.consumerpat.service;

import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.ExtractDTO;

@Service
public interface IExtractService {

	/**
	 * Método responsável por realizar uma compra.
	 * 
	 * @param extractDTO
	 */
	void buy(ExtractDTO extractDTO);
}
