package br.com.alelo.consumer.consumerpat.dto.request;


import br.com.alelo.consumer.consumerpat.type.CardType;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Data
@Entity
@EqualsAndHashCode
public class ConsumerRequestDto {

    Integer id;

    String name;

    @NotNull
    Integer documentNumber;

    Date birthDate;

    @NotNull
    ConsumerContactRequestDto contacts;

    @NotNull
    ConsumerAddressRequestDto address;

    @NotNull
    List<ConsumerCardRequestDto> cards;


    public Optional<ConsumerCardRequestDto> getCardType(final ConsumerRequestDto consumerRequest, CardType cardType) {
        return this.getCards()
                .stream()
                .filter(consumerCardRequestDto -> consumerCardRequestDto.getType().equals(cardType))
                .findFirst();
    }
}
