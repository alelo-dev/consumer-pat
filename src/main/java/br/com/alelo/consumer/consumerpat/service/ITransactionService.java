package br.com.alelo.consumer.consumerpat.service;

import java.util.List;

import br.com.alelo.consumer.consumerpat.dto.BuyDto;
import br.com.alelo.consumer.consumerpat.dto.ExtractDto;

public interface ITransactionService {

	public ExtractDto buy(BuyDto buyDto) throws Exception;

	public List<ExtractDto> consultExtract();

}
