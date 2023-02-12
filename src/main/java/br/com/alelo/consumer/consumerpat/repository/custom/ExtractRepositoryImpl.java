package br.com.alelo.consumer.consumerpat.repository.custom;

import br.com.alelo.consumer.consumerpat.entity.Extract;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExtractRepositoryImpl implements ExtractRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Extract> findTop10TransactionDay(LocalDate date) {

        String sQuery = "select e from Extract e order by e.amount desc ";

        Query query = em.createQuery(sQuery);
        query.setMaxResults(10);

        try{
            List<Extract> extracts = (List<Extract>) query.getResultList();
            return extracts;
        }catch(NoResultException e){
            return new ArrayList<Extract>();
        }

    }
}