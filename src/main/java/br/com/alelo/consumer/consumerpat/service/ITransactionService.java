package br.com.alelo.consumer.consumerpat.service;

import java.util.List;

import br.com.alelo.consumer.consumerpat.dto.BuyDto;
import br.com.alelo.consumer.consumerpat.dto.ExtractDto;
import br.com.alelo.consumer.consumerpat.dto.RequestExtractDto;
import br.com.alelo.consumer.consumerpat.entity.Card;

public interface ITransactionService {

	public ExtractDto buy(BuyDto buyDto) throws Exception;

	public void BuyFoodRule(BuyDto buyDto, Card card);

	public void BuyFuelRule(BuyDto buyDto, Card card);

	public void BuyDrugstoreRule(BuyDto buyDto, Card card);

	public List<ExtractDto> consultExtract(RequestExtractDto requestExtractDto);

}
