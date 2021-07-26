package br.com.alelo.consumer.consumerpat.respository;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 24/07/2021 | 13:50
 */

import br.com.alelo.consumer.consumerpat.entity.ConsumerContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerContactRepository extends JpaRepository<ConsumerContact, Integer>, IRepository<ConsumerContact> { }
