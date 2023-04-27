package br.com.alelo.consumer.consumerpat.excepion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CardNotFoundExeption extends RuntimeException {
    //TODO: implementar retorno de mensagens mais amigavel, implementar internacionalizaçã
}
