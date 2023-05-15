package br.com.alelo.consumer.consumerpat.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static org.springframework.http.HttpStatus.*;

/**
 * Request default response
 */
@Component
public class DefaultApiResponse {

	@Value("${spring.application.name}")
	private String applicationName;

	/**
	 * Generic response for collections.
	 *
	 * @param <E> Element
	 */
	public <E> ResponseEntity<Collection<E>> ok(Collection<E> entyties) {
		return new ResponseEntity<>(entyties, getHeaders(), OK);
	}

	/**
	 * Generic response for Object.
	 *
	 * @param <T> Type
	 * @param <T> Body values of type
	 */
	public <T> ResponseEntity<T> ok(T body) {
		return new ResponseEntity<>((T) body, getHeaders(), OK);
	}

	/**
	 * Set default headers to response
	 *
	 * @return HttpHeaders
	 */
	private HttpHeaders getHeaders() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("API", applicationName);
		return responseHeaders;
	}
}