package br.com.alelo.consumer.consumerpat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class to handle with request parameter exception.
 */
public class MissingRequestParamException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Modify exception handler to return 400 instead 500 when required param is null
     *
     * @param e the MissingServletRequestParameterException
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Required request param was not filled.")
    public void missingServletRequestParameterExceptionResolver(final MissingServletRequestParameterException e) {
        //Method to modify exception handler to return 400 instead 500 when required param is null
    }
}
