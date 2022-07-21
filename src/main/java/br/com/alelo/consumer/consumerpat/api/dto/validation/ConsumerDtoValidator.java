package br.com.alelo.consumer.consumerpat.api.dto.validation;

import br.com.alelo.consumer.consumerpat.api.dto.ConsumerDto;
import br.com.alelo.consumer.consumerpat.domain.exception.InvalidRequestException;
import org.springframework.stereotype.Component;

// OBSERVAÇÃO: assumirei que todos os dados do consumidor são obrigatórios.

@Component
public class ConsumerDtoValidator implements DtoValidator<ConsumerDto> {

    public static ConsumerDtoValidator of() {
        return new ConsumerDtoValidator();
    }

    @Override
    public void validate(ConsumerDto dto) throws InvalidRequestException {
        validateName(dto);
        validateDocumentNumber(dto);
        validateBirthDate(dto);
        validateMobilePhoneNumber(dto);
        validateResidencePhoneNumber(dto);
        validatePhoneNumber(dto);
        validateEmail(dto);
        validateStreet(dto);
        validateNumber(dto);
        validateCity(dto);
        validateCountry(dto);
        validatePortalCode(dto);
        validateFoodCardNumber(dto);
        validateFoodCardBalance(dto);
        validateFuelCardNumber(dto);
        validateFuelCardBalance(dto);
        validateDrugstoreCardNumber(dto);
        validateDrugstoreCardBalance(dto);
    }

    public static void validateIfNotNullDto(ConsumerDto dto) throws InvalidRequestException {
        if (dto == null) {
            throw InvalidRequestException.ofNoValidRequestDataFound();
        }
    }

    public static void validateId(ConsumerDto dto) throws InvalidRequestException {
        if (dto.getId() == null) {
            throw InvalidRequestException.ofNotNullFieldException("id");
        }
    }

    public static void validateName(ConsumerDto dto) throws InvalidRequestException {
        if (dto.getName() == null) {
            throw InvalidRequestException.ofNotNullFieldException("name");
        }

        if (dto.getName().isBlank()) {
            throw InvalidRequestException.ofNotBlankFieldException("name");
        }
    }

    public static void validateDocumentNumber(ConsumerDto dto) throws InvalidRequestException {
        if (dto.getDocumentNumber() == null) {
            throw InvalidRequestException.ofNotNullFieldException("documentNumber");
        }

        if (dto.getDocumentNumber() < 0) {
            throw InvalidRequestException.ofInvalidFieldException("documentNumber", "cannot be a negative number!");
        }
    }

    public static void validateBirthDate(ConsumerDto dto) throws InvalidRequestException {
        if (dto.getBirthDate() == null) {
            throw InvalidRequestException.ofNotNullFieldException("birthDate");
        }
    }

    public static void validateMobilePhoneNumber(ConsumerDto dto) throws InvalidRequestException {
        if (dto.getMobilePhoneNumber() == null) {
            throw InvalidRequestException.ofNotNullFieldException("mobilePhoneNumber");
        }

        if (dto.getMobilePhoneNumber() < 0) {
            throw InvalidRequestException.ofInvalidFieldException("documentNumber", "cannot be a negative number!");
        }
    }

    public static void validateResidencePhoneNumber(ConsumerDto dto) throws InvalidRequestException {
        if (dto.getMobilePhoneNumber() == null) {
            throw InvalidRequestException.ofNotNullFieldException("residencePhoneNumber");
        }

        if (dto.getMobilePhoneNumber() < 0) {
            throw InvalidRequestException.ofInvalidFieldException("residencePhoneNumber", "cannot be a negative number!");
        }
    }

    public static void validatePhoneNumber(ConsumerDto dto) throws InvalidRequestException {
        if (dto.getMobilePhoneNumber() == null) {
            throw InvalidRequestException.ofNotNullFieldException("phoneNumber");
        }

        if (dto.getMobilePhoneNumber() < 0) {
            throw InvalidRequestException.ofInvalidFieldException("phoneNumber", "cannot be a negative number!");
        }
    }

    public static void validateEmail(ConsumerDto dto) throws InvalidRequestException {
        if (dto.getEmail() == null) {
            throw InvalidRequestException.ofNotNullFieldException("email");
        }

        if (dto.getEmail().matches("\\w+@(\\w+\\.\\w+)+")) {
            throw InvalidRequestException.ofInvalidFieldException("email", "must be in a valid format!");
        }
    }

    public static void validateStreet(ConsumerDto dto) throws InvalidRequestException {
        if (dto.getStreet() == null) {
            throw InvalidRequestException.ofNotNullFieldException("street");
        }

        if (dto.getStreet().isBlank()) {
            throw InvalidRequestException.ofNotBlankFieldException("street");
        }
    }

    public static void validateNumber(ConsumerDto dto) throws InvalidRequestException {
        if (dto.getNumber() == null) {
            throw InvalidRequestException.ofNotNullFieldException("number");
        }

        if (dto.getNumber() < 0) {
            throw InvalidRequestException.ofInvalidFieldException("number", "cannot be a negative number!");
        }
    }

    public static void validateCity(ConsumerDto dto) throws InvalidRequestException {
        if (dto.getCity() == null) {
            throw InvalidRequestException.ofNotNullFieldException("city");
        }

        if (dto.getCity().isBlank()) {
            throw InvalidRequestException.ofNotBlankFieldException("city");
        }
    }

    public static void validateCountry(ConsumerDto dto) throws InvalidRequestException {
        if (dto.getCountry() == null) {
            throw InvalidRequestException.ofNotNullFieldException("country");
        }

        if (dto.getCountry().isBlank()) {
            throw InvalidRequestException.ofNotBlankFieldException("country");
        }
    }

    public static void validatePortalCode(ConsumerDto dto) throws InvalidRequestException {
        if (dto.getPortalCode() == null) {
            throw InvalidRequestException.ofNotNullFieldException("portalCode");
        }
    }

    public static void validateFoodCardNumber(ConsumerDto dto) throws InvalidRequestException {
        if (dto.getFoodCardNumber() == null) {
            throw InvalidRequestException.ofNotNullFieldException("foodCardNumber");
        }
    }

    public static void validateFoodCardBalance(ConsumerDto dto) throws InvalidRequestException {
        if (dto.getFoodCardBalance() == null) {
            throw InvalidRequestException.ofNotNullFieldException("foodCardBalance");
        }
    }

    public static void validateFuelCardNumber(ConsumerDto dto) throws InvalidRequestException {
        if (dto.getFuelCardNumber() == null) {
            throw InvalidRequestException.ofNotNullFieldException("fuelCardNumber");
        }
    }

    public static void validateFuelCardBalance(ConsumerDto dto) throws InvalidRequestException {
        if (dto.getFuelCardBalance() == null) {
            throw InvalidRequestException.ofNotNullFieldException("fuelCardBalance");
        }
    }

    public static void validateDrugstoreCardNumber(ConsumerDto dto) throws InvalidRequestException {
        if (dto.getDrugstoreCardNumber() == null) {
            throw InvalidRequestException.ofNotNullFieldException("drugstoreCardNumber");
        }
    }

    public static void validateDrugstoreCardBalance(ConsumerDto dto) throws InvalidRequestException {
        if (dto.getDrugstoreCardBalance() == null) {
            throw InvalidRequestException.ofNotNullFieldException("drugstoreCardBalance");
        }
    }

}
