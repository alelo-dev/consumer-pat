package br.com.alelo.consumer.consumerpat.config;

import br.com.alelo.consumer.consumerpat.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public void startDataBase() throws ParseException {
        this.dbService.startDB();
    }
}
