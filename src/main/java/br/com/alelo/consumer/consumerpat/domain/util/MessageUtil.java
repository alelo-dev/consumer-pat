package br.com.alelo.consumer.consumerpat.domain.util;

/**
 * Classe utilitária contrendo diversas constantes de mensagens da aplicação.
 */
public final class MessageUtil {

    public static final String MSG_NO_VALID_REQUEST_DATA_FOUND = "No valid request data found!";

    public static final String MSG_INVALID_FIELD = "The %s field %s";

    public static final String MSG_CONSUMER_ALREADY_REGISTERED = "Consumer already registered!";

    public static final String MSG_CONSUMER_NOT_FOUND = "Consumer not found!";

    public static final String MSG_BENEFIT_CARD_NOT_FOUND = "Benefit card not found!";

    public static final String MSG_CARD_BALANCE_CANNOT_BE_CHANGED
            = "Card balance cannot be changed!";

    public static final String MSG_FAILED_TO_DELETE_CONSUMER = "Failed to delete consumer!";

    public static final String MSG_REQUIRED_FIELD = "The %s field is a mandatory field!";

    public static final String MSG_INVALID_VALUE_FIELD = "The %s field contains an invalid value!";

    private MessageUtil() {
        super();
    }
}
