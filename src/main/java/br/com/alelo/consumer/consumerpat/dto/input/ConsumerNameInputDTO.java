package br.com.alelo.consumer.consumerpat.dto.input;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ConsumerNameInputDTO {

    @NotNull
    private String name;

}
