package br.com.alelo.consumer.consumerpat.application.web.wrappers;

import br.com.alelo.consumer.consumerpat.application.web.requests.DebitCardRequest;
import br.com.alelo.consumer.consumerpat.domain.dto.DebitCard;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor
public class DebitCardWrapper {

    private DebitCard debitCard;

    public DebitCardWrapper(int consumerId, DebitCardRequest debitCardRequest) {
        this.debitCard = new DebitCard();

        BeanUtils.copyProperties(debitCardRequest, debitCard);
        debitCard.setConsumerId(consumerId);
    }

    public DebitCard toModel() {
        return debitCard;
    }
}
