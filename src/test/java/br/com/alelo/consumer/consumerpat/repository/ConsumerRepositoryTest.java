package br.com.alelo.consumer.consumerpat.repository;


import br.com.alelo.consumer.consumerpat.entity.*;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
public class ConsumerRepositoryTest {

    @Autowired
    ConsumerRepository repository;

    @Test
    public void saveAndLoadData(){
        Consumer entrada = new Consumer("windson",6661,new Date());
        Consumer retornoEntrada = repository.save(entrada);
        Assert.notNull(retornoEntrada.getId(),"O id deve ser preenchido pelo SGBD");
        Optional<Consumer> load = repository.findById(retornoEntrada.getId());
        Assert.isTrue(load.isPresent(),"O registro deve estar presente no banco");
    }

    @Test
    public void addEnderecoEhContato(){
        Consumer entrada = new Consumer("windson",6661,new Date());
        entrada.addAddress(new Address("Rua",666,"cidade","Estado",6662));
        entrada.addContact(new Contact(ContactType.Email,"windson@windson.com"));
        Consumer retornoEntrada = repository.save(entrada);
        Assert.notNull(retornoEntrada.getAddresses().stream().findFirst().get().getId(),"O endereço deve receber id do SGBD");
        Assert.notNull(retornoEntrada.getContacts().stream().findFirst().get().getId(),"O Contato deve receber id do SGBD");
    }

    @Test
    @Transactional
    public void addCartao(){
        Consumer entrada = new Consumer("windson",6661,new Date());
        entrada.addCard(new Card(66666,CardType.FOOD));
        Consumer retornoEntrada = repository.save(entrada);
        Set<Card> load = repository.findById(retornoEntrada.getId()).get().getCards();
        Assert.isTrue(load.size() > 0,"O endereço deve receber id do SGBD");
    }
}
