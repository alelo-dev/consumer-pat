package br.com.alelo.consumer.consumerpat.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@NoArgsConstructor
public class MessageService {

    @Autowired
    private MessageSource messageSource;

    private final Locale enUS = new Locale("en", "US");

    public String get(String messageType, Object... args) {
        return this.messageSource.getMessage(messageType, args, this.enUS);
    }
}
