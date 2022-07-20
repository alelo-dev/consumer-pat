package br.com.alelo.consumer.consumerpat.usecase.strategy;

import br.com.alelo.consumer.consumerpat.controller.model.BuyRequest;

public interface ProcessCardStrategy {
    boolean shouldExecute(BuyRequest input);
    boolean execute(BuyRequest input);
}
