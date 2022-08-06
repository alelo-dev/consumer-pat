package br.com.alelo.consumer.consumerpat.response;

import br.com.alelo.consumer.consumerpat.domain.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtractResponse {

    private String purchaseCode;

    private Type establishmentType;

    private String establishmentName;

    private List<String> products;

    private LocalDate dateBuy;

    private String cardNumber;

    private BigDecimal amount;
}
