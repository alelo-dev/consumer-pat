package br.com.alelo.consumer.consumerpat.core.exception;

import java.util.HashMap;

public class InvalidRechargeException extends BadRequestException {
    public InvalidRechargeException() {
        this.fieldsAndMessages = new HashMap<>();

        this.fieldsAndMessages.put("recharge", "invalid.recharge");
    }
}
