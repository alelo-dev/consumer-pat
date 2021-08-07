package br.com.alelo.consumer.consumerpat.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enuns.EstablishmentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExtractDTO {

	private String productDescription;
    private Long establishmentId;
    private String establishmentName;
    private BigDecimal value;
    private EstablishmentTypeEnum establishmentType;
    private String cardNumber;

	public Extract toEntity() {
		return new Extract(null, productDescription, establishmentId, establishmentName, LocalDate.now(), establishmentType.getProcessor().calculate(value), cardNumber, establishmentType);
	}
}
