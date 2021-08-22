package br.com.alelo.consumerpat.core.exception;

import java.util.HashMap;

public class InvalidBalanceException extends BadRequestException {
    public InvalidBalanceException() {
        this.fieldsAndMessages = new HashMap<>();
        this.fieldsAndMessages.put("balance", "invalid.balance");
    }
}
