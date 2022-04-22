package br.com.alelo.consumer.consumerpat.dto.input;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestInputDTO {

    @NotNull
    private Long establishmentId;

    @NotNull
    private Long cardId;

    @NotNull
    private Long consumerId;

    @NotNull
    private Long productId;


}
