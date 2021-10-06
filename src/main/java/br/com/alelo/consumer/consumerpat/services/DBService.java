package br.com.alelo.consumer.consumerpat.services;

import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * Classe criada para iniciar uma carga no banco sempre que
 * projeto estiver compilando. Algumas instancias serão criadas
 * e persistidas no banco de dados h2
 */
@Service
public interface DBService {

    void startDB() throws ParseException;

}
