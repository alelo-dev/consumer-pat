package br.com.alelo.consumer.consumerpat.api.dto.validation;

import br.com.alelo.consumer.consumerpat.api.dto.CardBalanceDto;
import br.com.alelo.consumer.consumerpat.domain.exception.InvalidRequestException;

// OBSERVAÇÕES:

// 1. Eu poderia ter utilizado Bean Validation para simplificar porém
// um dos pré-requisitos do teste foi não adicionar bibliotecas ou frameworks.

// 2. Existem soluções mas elegantes mas o tempo não me permitiu aplicá-las
// de uma maneira que eu garantisse a qualidade do que estou entregando.

public class CardBalanceDtoValidator implements DtoValidator<CardBalanceDto> {
    @Override
    public void validate(CardBalanceDto dto) throws InvalidRequestException {
        validateCardNumber(dto);
        validateCardType(dto);
        validateValue(dto);
    }

    public static void validateCardNumber(CardBalanceDto dto) throws InvalidRequestException {
        if (dto.getCardNumber() == null) {
            throw InvalidRequestException.ofNotNullFieldException("cardNumber");
        }
    }

    public static void validateCardType(CardBalanceDto dto) throws InvalidRequestException {
        if (dto.getCardType() == null) {
            throw InvalidRequestException.ofNotNullFieldException("cardType");
        }
    }

    public static void validateValue(CardBalanceDto dto) throws InvalidRequestException {
        if (dto.getValue() == null) {
            throw InvalidRequestException.ofNotNullFieldException("value");
        }
    }
}
