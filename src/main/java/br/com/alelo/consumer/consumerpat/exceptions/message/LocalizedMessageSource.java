package br.com.alelo.consumer.consumerpat.exceptions.message;

import br.com.alelo.consumer.consumerpat.exceptions.AppErrors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalizedMessageSource implements BaseMessageSource {
    private final MessageSource messageSource;

    public String getMessage(AppErrors error, Object[] params) {
        return getMessage(error.getMessageRes(), params);
    }

    public String getMessage(String resource, Object[] params) {
        return messageSource.getMessage(resource, params, LocaleContextHolder.getLocale());
    }
}