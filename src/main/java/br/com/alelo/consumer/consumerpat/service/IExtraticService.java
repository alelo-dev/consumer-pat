package br.com.alelo.consumer.consumerpat.service;

import java.util.List;

import br.com.alelo.consumer.consumerpat.dto.BuyDto;
import br.com.alelo.consumer.consumerpat.dto.ExtractDto;
import br.com.alelo.consumer.consumerpat.dto.RequestExtractDto;

public interface IExtraticService {
	
    public ExtractDto saveExtract(BuyDto buyDto);

	List<ExtractDto> extractAllData(RequestExtractDto requestExtractDto);

}
