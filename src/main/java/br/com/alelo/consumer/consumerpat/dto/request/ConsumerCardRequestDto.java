package br.com.alelo.consumer.consumerpat.dto.request;


import br.com.alelo.consumer.consumerpat.type.CardType;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.util.Date;
import java.util.Optional;


@Data
@Entity
@EqualsAndHashCode
public class ConsumerCardRequestDto {
    @NotNull
    CardType type;

    @NotNull
    Integer number;

    @NotNull
    Double balance;
}
