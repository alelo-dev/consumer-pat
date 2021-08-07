package br.com.alelo.consumer.consumerpat.mock.dto;

import java.math.BigDecimal;

import br.com.alelo.consumer.consumerpat.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.enuns.EstablishmentTypeEnum;

public class ExtractDTOMock {

	private ExtractDTOMock() {}
	
	public static ExtractDTO createFood(Double value) {
		return ExtractDTO.builder()
				.productDescription("Pães e Doces")
				.establishmentId(1L)
				.establishmentName("Panificadora do Zé")
				.value(BigDecimal.valueOf(value))
				.establishmentType(EstablishmentTypeEnum.FOOD)
				.cardNumber("1111222233334444")
				.build();
	}
	
	public static ExtractDTO createDrugstore(Double value) {
		return ExtractDTO.builder()
				.productDescription("Remédios")
				.establishmentId(1L)
				.establishmentName("Drogasil")
				.value(BigDecimal.valueOf(value))
				.establishmentType(EstablishmentTypeEnum.DRUGSTORE)
				.cardNumber("0000111100001111")
				.build();
	}
	
	public static ExtractDTO createFuel(Double value) {
		return ExtractDTO.builder()
				.productDescription("Abastecimento e troca de óleo")
				.establishmentId(1L)
				.establishmentName("Posto Ipiranga")
				.value(BigDecimal.valueOf(value))
				.establishmentType(EstablishmentTypeEnum.FUEL)
				.cardNumber("9999000099990000")
				.build();
	}
}
