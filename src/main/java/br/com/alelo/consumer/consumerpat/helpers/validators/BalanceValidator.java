package br.com.alelo.consumer.consumerpat.helpers.validators;

import br.com.alelo.consumer.consumerpat.constants.ErrorCodeEnum;
import br.com.alelo.consumer.consumerpat.constants.ErrorMessages;
import br.com.alelo.consumer.consumerpat.constants.ValidationConstraints;
import br.com.alelo.consumer.consumerpat.exceptions.PurchaseException;
import org.springframework.util.StringUtils;

public class BalanceValidator {

    public static void hasEnoughBalanceValidator(final double currentBalance, final double purchaseValue) {
        double newBalance = currentBalance - purchaseValue;
        if (newBalance < 0) {
                throw new PurchaseException(
                    ErrorCodeEnum.NOT_ENOUGH_BALANCE,
                    ErrorMessages.PRECONDITION_FAILED, StringUtils.replace(
                    ValidationConstraints.NOT_ENOUGH_BALANCE, "{}", String.valueOf(purchaseValue)));
        }
    }

}
