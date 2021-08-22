package br.com.alelo.consumerpat.dataprovider.dao;


import br.com.alelo.consumerpat.core.dataprovider.dao.AddressDao;
import br.com.alelo.consumerpat.core.dataprovider.entity.AddressEntity;
import br.com.alelo.consumerpat.dataprovider.repository.AddressRepository;
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
