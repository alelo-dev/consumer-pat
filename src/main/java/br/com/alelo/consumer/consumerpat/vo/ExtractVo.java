package br.com.alelo.consumer.consumerpat.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExtractVo {

    private Long cardNumber;
    private Integer establishmentId;
    private String establishmentName;
    private BigDecimal value;
    private String productDescription;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate dateBuy;
}
