package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.enumerator.CardType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(NON_EMPTY)
public class CardDTO {

    private Integer cardNumber;
    private CardType type;
    private ClientDTO client;
    private double balance;
}
