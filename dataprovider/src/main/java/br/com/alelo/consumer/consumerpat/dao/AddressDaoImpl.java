package br.com.alelo.consumer.consumerpat.dao;


import br.com.alelo.consumer.consumerpat.dataprovider.dao.AddressDao;
import br.com.alelo.consumer.consumerpat.dataprovider.entity.AddressEntity;
import br.com.alelo.consumer.consumerpat.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDaoImpl implements AddressDao {

    @Autowired
    private AddressRepository repository;

    @Override
    public AddressEntity save(AddressEntity entity) {
        return this.repository.save(entity);
    }
}
