package br.com.alelo.consumer.consumerpat.respository;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 25/07/2021 | 10:41
 */

import br.com.alelo.consumer.consumerpat.entity.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthTokenRepository extends JpaRepository<AuthToken, Integer> {

    Optional<AuthToken> findByToken(String token);

}
