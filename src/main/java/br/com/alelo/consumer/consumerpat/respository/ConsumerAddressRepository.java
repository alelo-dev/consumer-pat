package br.com.alelo.consumer.consumerpat.respository;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 24/07/2021 | 13:49
 */

import br.com.alelo.consumer.consumerpat.entity.ConsumerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerAddressRepository extends JpaRepository<ConsumerAddress, Integer>, IRepository<ConsumerAddress> {

}
