package br.com.alelo.consumer.consumerpat.application.web.wrappers;

import br.com.alelo.consumer.consumerpat.application.web.requests.CardBalanceRequest;
import br.com.alelo.consumer.consumerpat.domain.dto.CardBalance;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor
public class CardBalanceRequestWrapper {

    private CardBalance cardBalance;

    public CardBalanceRequestWrapper(int consumerId, CardBalanceRequest cardBalanceRequest) {
        this.cardBalance = new CardBalance();

        BeanUtils.copyProperties(cardBalanceRequest, cardBalance);
        cardBalance.setConsumerId(consumerId);
    }

    public CardBalance toModel() {
        return cardBalance;
    }

}
