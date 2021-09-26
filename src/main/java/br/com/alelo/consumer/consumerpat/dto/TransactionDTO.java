package br.com.alelo.consumer.consumerpat.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(NON_EMPTY)
public class TransactionDTO {
    private Long id;
    private EstablishmentDTO establishment;
    private String productDescription;
    private CardDTO card;
    private LocalDateTime dateBuy;
    private double value;
}
