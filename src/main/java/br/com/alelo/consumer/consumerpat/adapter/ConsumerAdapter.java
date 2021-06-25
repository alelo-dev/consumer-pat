package br.com.alelo.consumer.consumerpat.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.vo.ConsumerVO;

public class ConsumerAdapter {
	
	
	public static ConsumerVO modelToVo(final Consumer entity) {
		Assert.notNull(entity, "Objeto não pode ser nulo!");

		return ConsumerVO
			.builder()
				.id(entity.getId())
				.name(entity.getName())
				.documentNumber(entity.getDocumentNumber())
				.birthDate(entity.getBirthDate())
				.mobilePhoneNumber(entity.getMobilePhoneNumber())
				.residencePhoneNumber(entity.getResidencePhoneNumber())
				.phoneNumber(entity.getPhoneNumber())
				.email(entity.getEmail())
				.street(entity.getStreet())
				.number(entity.getNumber())
				.city(entity.getCity())
				.country(entity.getCountry())
				.portalCode(entity.getPortalCode())
				.foodCardNumber(entity.getFoodCardNumber())
				.foodCardBalance(entity.getFoodCardBalance())
				.fuelCardNumber(entity.getFuelCardNumber())
				.fuelCardBalance(entity.getFuelCardBalance())
				.drugstoreNumber(entity.getDrugstoreNumber())
				.drugstoreCardBalance(entity.getDrugstoreCardBalance())
		.build();
	}

	public static Consumer voToModel(ConsumerVO vo) {
		Assert.notNull(vo, "Objeto não pode ser nulo!");

		return Consumer
			.builder()
				.id(vo.getId())
				.name(vo.getName())
				.documentNumber(vo.getDocumentNumber())
				.birthDate(vo.getBirthDate())
				.mobilePhoneNumber(vo.getMobilePhoneNumber())
				.residencePhoneNumber(vo.getResidencePhoneNumber())
				.phoneNumber(vo.getPhoneNumber())
				.email(vo.getEmail())
				.street(vo.getStreet())
				.number(vo.getNumber())
				.city(vo.getCity())
				.country(vo.getCountry())
				.portalCode(vo.getPortalCode())
				.foodCardNumber(vo.getFoodCardNumber())
				.foodCardBalance(vo.getFoodCardBalance())
				.fuelCardNumber(vo.getFuelCardNumber())
				.fuelCardBalance(vo.getFuelCardBalance())
				.drugstoreNumber(vo.getDrugstoreNumber())
				.drugstoreCardBalance(vo.getDrugstoreCardBalance())
		.build();
	}

	public static List<ConsumerVO> modelToVo(final List<Consumer> listEntity) {
		if (CollectionUtils.isEmpty(listEntity)) {
			return new ArrayList<>();
		}

		return listEntity.stream().map(ConsumerAdapter::modelToVo).collect(Collectors.toList());

	}

	public static List<Consumer> voToModel(final List<ConsumerVO> listVo) {
		if (CollectionUtils.isEmpty(listVo)) {
			return new ArrayList<>();
		}

		return listVo.stream().map(ConsumerAdapter::voToModel).collect(Collectors.toList());

	}

	public static Consumer voToModel(ConsumerVO vo, Consumer entity) {
		
		return Consumer
			.builder()
				.id(entity.getId())
				.name(vo.getName())
				.documentNumber(vo.getDocumentNumber())
				.birthDate(vo.getBirthDate())
				.mobilePhoneNumber(vo.getMobilePhoneNumber())
				.residencePhoneNumber(vo.getResidencePhoneNumber())
				.phoneNumber(vo.getPhoneNumber())
				.email(vo.getEmail())
				.street(vo.getStreet())
				.number(vo.getNumber())
				.city(vo.getCity())
				.country(vo.getCountry())
				.portalCode(vo.getPortalCode())
				.foodCardNumber(vo.getFoodCardNumber())
				.fuelCardNumber(vo.getFoodCardNumber())
				.drugstoreNumber(vo.getDrugstoreNumber())
			.build();
		
	}

}
