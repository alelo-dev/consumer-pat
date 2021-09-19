package br.com.alelo.consumer.consumerpat.domain;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.hasLength;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.alelo.consumer.consumerpat.infrastructure.exception.types.BusinessException;
import br.com.alelo.consumer.consumerpat.infrastructure.exception.types.ErrorMessageDto;
import lombok.Data;

@Data
@Entity
public class Consumer {

	public static final BusinessException CARD_IS_NULL = new BusinessException("card is null");
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String documentNumber;
	private LocalDate birthDate;

	// contacts
	private String mobilePhoneNumber;
	private String residencePhoneNumber;
	private String email;

	// Address
	private String addressStreet;
	private String addressNumber;
	private String addressCity;
	private String addressCountry;
	private String addressPostalCode;

	@OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL)
	public List<Card> cards;

	public void addCard(Card card) {

		if (isNull(card)) {
			throw CARD_IS_NULL;
		}

		if (this.cards == null) {
			this.cards = new ArrayList<>();
		}
		cards.add(card);
	}

	public void validated() {
		ErrorMessageDto errorsDto = new ErrorMessageDto();
		validatePersonalData(errorsDto);
		validateContactDetails(errorsDto);
		validateAddress(errorsDto);
		if (errorsDto.containsErrors()) {
			throw new BusinessException("error validating the consumer", errorsDto);
		}
	}

	private void validatePersonalData(ErrorMessageDto error) {
		if (!hasLength(name)) {
			error.addAdditionalMessages("name not found");
		}
		if (!hasLength(documentNumber)) {
			error.addAdditionalMessages("documentNumber not found");
		}
		if (isNull(birthDate)) {
			error.addAdditionalMessages("birthDate not found");
		} else {
			if (birthDate.compareTo(LocalDate.now()) >= 0) {
				error.addAdditionalMessages("date of birth cannot be in the future");
			}
		}
	}

	private void validateContactDetails(ErrorMessageDto error) {
		if (!hasLength(mobilePhoneNumber)) {
			error.addAdditionalMessages("mobilePhoneNumber not found");
		}

		if (!hasLength(residencePhoneNumber)) {
			error.addAdditionalMessages("residencePhoneNumber not found");
		}

		if (!hasLength(email)) {
			error.addAdditionalMessages("email not found");
		}
	}

	private void validateAddress(ErrorMessageDto error) {
		if (isNull(addressStreet)) {
			error.addAdditionalMessages("address street not found");
		}
		if (isNull(addressNumber)) {
			error.addAdditionalMessages("address number not found");
		}
		if (isNull(addressCity)) {
			error.addAdditionalMessages("address city not found");
		}
		if (isNull(addressCountry)) {
			error.addAdditionalMessages("address country not found");
		}

		if (isNull(addressPostalCode)) {
			error.addAdditionalMessages("address postalCode not found");
		}
	}

}
