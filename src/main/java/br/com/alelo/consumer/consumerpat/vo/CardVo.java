package br.com.alelo.consumer.consumerpat.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardVo {

    private Long number;
    private BigDecimal balance;
    private Long type;
    private Integer consumerId;

}
