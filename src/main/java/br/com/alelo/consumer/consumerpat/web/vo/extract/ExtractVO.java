package br.com.alelo.consumer.consumerpat.web.vo.extract;

import br.com.alelo.consumer.consumerpat.model.entity.Card;
import br.com.alelo.consumer.consumerpat.model.entity.Extract;
import br.com.alelo.consumer.consumerpat.utils.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "id", "establishmentId", "establishmentName", "productDescription", "dateBuy", "value", "cardNumber" })
public class ExtractVO implements Serializable {

    private Long id;

    private Long establishmentId;

    private String establishmentName;

    private String productDescription;

    @JsonFormat(pattern = Constants.DATETIME_FORMAT_PATTERN)
    @DateTimeFormat(pattern = Constants.DATETIME_FORMAT_PATTERN)
    private LocalDateTime dateBuy;

    private BigDecimal value;

    private Long cardNumber;

    public static ExtractVO from(Extract extract) {
        return ExtractVO.builder()
            .id(extract.getId())
            .establishmentId(extract.getEstablishmentId())
            .establishmentName(extract.getEstablishmentName())
            .productDescription(extract.getProductDescription())
            .dateBuy(extract.getDateBuy())
            .value(extract.getValue())
            .cardNumber(Optional.ofNullable(extract.getCard())
                    .map(Card::getNumber)
                    .orElseGet(() -> null))
            .build();
    }
}
