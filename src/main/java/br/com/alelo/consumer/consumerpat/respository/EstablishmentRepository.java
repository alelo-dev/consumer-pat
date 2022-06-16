package br.com.alelo.consumer.consumerpat.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.enums.Type;

public interface EstablishmentRepository extends JpaRepository<Establishment, Integer> {

	String FILTER_ESTABLISHMENTO_BY_TIPO_AND_NOME = "select b from Establishment b where UPPER(b.name) = UPPER(?1) and type = (?2)";

	@Query(FILTER_ESTABLISHMENTO_BY_TIPO_AND_NOME)
	Establishment consultarPorTipoENome(String nome, Type tipo);
}
