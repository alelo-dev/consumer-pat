package br.com.alelo.consumer.consumerpat.dao;

import br.com.alelo.consumer.consumerpat.dataprovider.dao.ContactDao;
import br.com.alelo.consumer.consumerpat.dataprovider.entity.ContactEntity;
import br.com.alelo.consumer.consumerpat.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ContactDaoImpl implements ContactDao {

    @Autowired
    private ContactRepository repository;

    @Override
    public ContactEntity save(ContactEntity entity) {
        return this.repository.save(entity);
    }
}
