package br.com.alelo.consumer.consumerpat.infra.handler.exception;

import br.com.alelo.consumer.consumerpat.infra.handler.model.ErrorModel;
import lombok.Getter;

/**
 * Class comments go here...
 *
 * @author André Franco
 * @version 1.0 05/12/2020
 */
public abstract class ApiException extends RuntimeException {

    @Getter
    private final int code;


    protected ApiException(String msg, int code) {
        super(msg);
        this.code = code;
    }



    /**
     * Gets error model.
     *
     * @return the error model
     */
    public ErrorModel getErrorModel() {
        return ErrorModel
            .builder()
            .code(this.code)
            .message(this.getMessage()).build();
    }

}
