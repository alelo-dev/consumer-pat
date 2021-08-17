package br.com.alelo.consumer.consumerpat.exceptions.message;

import br.com.alelo.consumer.consumerpat.exceptions.AppErrors;
import org.springframework.stereotype.Component;

@Component
public interface BaseMessageSource {
    String getMessage(String resource, Object[] params);
    String getMessage(AppErrors error, Object[] params);
}