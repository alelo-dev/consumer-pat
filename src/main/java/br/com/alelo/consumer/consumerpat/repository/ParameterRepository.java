package br.com.alelo.consumer.consumerpat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alelo.consumer.consumerpat.entity.Parameter;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Long> {

	@Query("SELECT a from Parameter a WHERE a.nameParameter = :parameterName")
	Parameter getParameterByName(@Param("parameterName") String parameterName);

}
