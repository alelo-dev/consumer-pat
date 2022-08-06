package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.request.PurchaseRequest;
import br.com.alelo.consumer.consumerpat.response.ExtractResponse;

public interface PurchaseService {

    ExtractResponse purchase(PurchaseRequest purchaseRequest);

    ExtractResponse getByPurchaseCode(String purchaseCode);
}
