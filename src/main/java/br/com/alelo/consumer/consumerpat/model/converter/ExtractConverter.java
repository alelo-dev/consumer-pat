package br.com.alelo.consumer.consumerpat.model.converter;

import java.util.List;
import java.util.stream.Collectors;

import br.com.alelo.consumer.consumerpat.model.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.model.entity.Extract;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExtractConverter {

	public static Extract toEntity(final ExtractDTO dto) {
		return Extract.builder()
				.establishmentNameId(dto.getEstablishmentNameId())
				.establishmentName(dto.getEstablishmentName())
				.productDescription(dto.getProductDescription())
				.dateBuy(dto.getDateBuy())
				.consumerCard(ConsumerCardConverter.toEntity(dto.getConsumerCard()))
				.value(dto.getValue())
				.build();
	}

	public static ExtractDTO toDTO(final Extract extract) {
		return ExtractDTO.builder()
				.establishmentNameId(extract.getEstablishmentNameId())
				.establishmentName(extract.getEstablishmentName())
				.productDescription(extract.getProductDescription())
				.dateBuy(extract.getDateBuy())
				.consumerCard(ConsumerCardConverter.toDTO(extract.getConsumerCard()))
				.value(extract.getValue())
				.build();
	}

	public static List<ExtractDTO> toListExtractDTO(final List<Extract> listExtract) {
		return listExtract.stream().map(ExtractConverter::toDTO).collect(Collectors.toList());
	}

}