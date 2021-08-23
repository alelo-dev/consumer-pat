package br.com.alelo.consumerpat.core.domain;

import br.com.alelo.consumerpat.core.enumeration.EstablishmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExtractDomain {

    private Long id;
    private String establishmentName;
    private String productDescription;
    private LocalDateTime dateBuy;
    private BigDecimal value;
    private EstablishmentType establishmentType;
    private CardDomain card;

    public void setId(Long id) {
        this.id = id;
    }
}
