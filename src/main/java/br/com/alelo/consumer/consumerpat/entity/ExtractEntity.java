package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.alelo.consumer.consumerpat.dto.v2.ExtractDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ExtractEntity {

    // Usaria MApstruct
    public ExtractEntity(ExtractDTO dto) {

        if (dto != null) {
            this.id = dto.getId();
            this.cardNumber = dto.getCardNumber();
            this.dateBuy = dto.getDateBuy();
            this.establishmentName = dto.getEstablishmentName();
            this.establishmentNameId = dto.getEstablishmentNameId();
            this.productDescription = dto.getProductDescription();
            this.value = dto.getValue();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer establishmentNameId;
    private String establishmentName;
    private String productDescription;
    private LocalDate dateBuy;
    private Integer cardNumber;
    private BigDecimal value;
}
