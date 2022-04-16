package br.com.alelo.consumer.consumerpat.dto.request;


import br.com.alelo.consumer.consumerpat.type.CardType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ConsumerRequestDto {

    Integer id;

    String name;

    @NotNull
    String documentNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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
