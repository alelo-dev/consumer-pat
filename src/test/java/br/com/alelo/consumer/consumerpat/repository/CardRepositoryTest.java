package br.com.alelo.consumer.consumerpat.repository;


import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.CardHistory;
import br.com.alelo.consumer.consumerpat.entity.CardType;
import br.com.alelo.consumer.consumerpat.respository.CardHistoryRepository;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.List;


@SpringBootTest
public class CardRepositoryTest {

    @Autowired
    CardRepository repository;
    @Autowired
    CardHistoryRepository cardHistoryRepository;


    @Test
    @Order(1)
    public void criaCartao() throws Exception {
        Card card = new Card(1236, CardType.FOOD);
        repository.save(card);
    }

    @Test
    @Order(2)
    @Transactional
    public void fazOperacaoDeposito() throws Exception {
        Card findCartao = repository.findById(1236).get();
        findCartao.addBalance(666);
        Card retorno = repository.save(findCartao);
        Assert.isTrue(retorno.getCardHistory().size() == 1, "Deve conter um item em historico do cartao");
        Assert.isTrue(retorno.getCardHistory().get(0).getValue().doubleValue() == 666, "Deve conter 666 no unico registro de historico");
        Assert.notNull(retorno.getCardHistory().get(0).getId(), "Deve existir um pk para historico");
    }


}
