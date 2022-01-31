package br.com.alelo.consumer.consumerpat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ConsumerTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerTestApplication.class, args);
    }

}
