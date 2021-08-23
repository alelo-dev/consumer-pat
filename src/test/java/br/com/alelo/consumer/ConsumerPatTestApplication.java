package br.com.alelo.consumer;

import br.com.alelo.consumerpat.ConsumerPatApplication;
import br.com.alelo.consumerpat.dataprovider.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(classes = ConsumerPatApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConsumerPatTestApplication {

    @Autowired
    protected TestRestTemplate testRestTemplate;

    @Autowired
    protected ConsumerRepository consumerRepository;

    @Autowired
    protected CardRepository cardRepository;

    @Autowired
    protected ExtractRepository extractRepository;

    @Autowired
    protected AddressRepository addressRepository;

    @Autowired
    protected ContactRepository contactRepository;


    @LocalServerPort
    private int port;

    @BeforeEach
    void beforeEach() {
        this.extractRepository.deleteAll();
        this.cardRepository.deleteAll();
        this.addressRepository.deleteAll();
        this.contactRepository.deleteAll();
        this.consumerRepository.deleteAll();
    }

    private String getHost() {
        return "http://localhost:" + port;
    }

    protected String getUrl(String uri) {
        return this.getHost() + uri;
    }
}
