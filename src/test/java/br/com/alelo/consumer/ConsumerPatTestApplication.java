package br.com.alelo.consumer;

import br.com.alelo.consumerpat.ConsumerPatApplication;
import br.com.alelo.consumerpat.dataprovider.jpa.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(classes = ConsumerPatApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConsumerPatTestApplication {

    @Autowired
    protected TestRestTemplate testRestTemplate;

    @Autowired
    protected ConsumerJpaRepository consumerJpaRepository;

    @Autowired
    protected CardJpaRepository cardJpaRepository;

    @Autowired
    protected ExtractJpaRepository extractJpaRepository;

    @Autowired
    protected AddressJpaRepository addressJpaRepository;

    @Autowired
    protected ContactJpaRepository contactJpaRepository;


    @LocalServerPort
    private int port;

    @BeforeEach
    void beforeEach() {
        this.extractJpaRepository.deleteAll();
        this.cardJpaRepository.deleteAll();
        this.addressJpaRepository.deleteAll();
        this.contactJpaRepository.deleteAll();
        this.consumerJpaRepository.deleteAll();
    }

    private String getHost() {
        return "http://localhost:" + port;
    }

    protected String getUrl(String uri) {
        return this.getHost() + uri;
    }
}
