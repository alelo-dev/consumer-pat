package br.com.alelo.consumerpat.dataprovider.dao;

import br.com.alelo.consumerpat.core.dataprovider.dao.ExtractDao;
import br.com.alelo.consumerpat.core.dataprovider.entity.ExtractEntity;
import br.com.alelo.consumerpat.dataprovider.repository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ExtractDaoImpl implements ExtractDao {

    @Autowired
    private ExtractRepository repository;

    @Override
    public ExtractEntity save(ExtractEntity entity) {
        return this.repository.save(entity);
    }
}
