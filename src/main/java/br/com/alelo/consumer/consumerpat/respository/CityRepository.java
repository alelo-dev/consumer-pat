package br.com.alelo.consumer.consumerpat.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.entity.City;

public interface CityRepository extends JpaRepository<City, Integer> {

    Optional<City> findByNameAndCountryId(String name, Integer countryId);

}
