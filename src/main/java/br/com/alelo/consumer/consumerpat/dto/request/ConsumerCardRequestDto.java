package br.com.alelo.consumer.consumerpat.dto.request;


import br.com.alelo.consumer.consumerpat.type.CardType;
import com.sun.istack.NotNull;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ConsumerCardRequestDto {
    @NotNull
    CardType type;

    @NotNull
    Integer number;

    @NotNull
    Double balance;
}
