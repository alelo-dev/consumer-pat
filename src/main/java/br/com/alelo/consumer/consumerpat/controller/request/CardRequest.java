package br.com.alelo.consumer.consumerpat.controller.request;

import br.com.alelo.consumer.consumerpat.domain.card.CardType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardRequest {

    @Getter
    @JsonProperty("card_number")
    private String cardNumber;

    @Getter
    @JsonProperty("balance")
    private double balance;

    public CardType getCardType() {
        final String substring = this.cardNumber.substring(0, 4);
        return CardType.getTypeByRange(substring);
    }
}
