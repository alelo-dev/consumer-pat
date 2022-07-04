package br.com.alelo.consumer.consumerpat.repository;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ExtractTest {

    @Autowired
    ExtractRepository repository;
 
    @BeforeEach
    private void setUp() throws ParseException {

        Extract extract = new Extract("establishmentName", "productDescription", new Date(), 123456789, 1000);
        extract.setEstablishmentNameId(1);
        extract.setEstablishmentName("Teste do teste");
        extract.setProductDescription("Produto beta");
        extract.setDateBuy(new Date());
        extract.setCardNumber(123456789);
        extract.setValue(1000);

        repository.save(extract);

        Extract extract2 = new Extract("establishmentName", "productDescription", new Date(), 123456789, 1000);
        extract2.setEstablishmentNameId(1);
        extract2.setEstablishmentName("Teste do teste");
        extract2.setProductDescription("Produto beta");
        extract2.setDateBuy(new Date());
        extract2.setCardNumber(123456789);
        extract2.setValue(1000);
        repository.save(extract2);
    }


    @Test
    public void getList() throws ParseException{
        List<Extract> extracts = repository.getAllExtractList();
        Assertions.assertThat(extracts).isNotNull();
    }
}
