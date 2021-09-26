package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.service.dto.request.ExtractRequest;
import br.com.alelo.consumer.consumerpat.service.dto.response.ExtractResponse;

public interface ExtractService {

	ExtractResponse buy (ExtractRequest request);
}
