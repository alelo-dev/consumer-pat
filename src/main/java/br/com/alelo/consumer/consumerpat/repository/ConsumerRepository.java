package br.com.alelo.consumer.consumerpat.repository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

	@Query(nativeQuery = true, value = "select * from Consumer")
	List<Consumer> getAllConsumersList();

	@Query(nativeQuery = true, value = "select * from Consumer where FOOD_CARD_NUMBER = ? ")
	Consumer findByFoodCardNumber(int cardNumber);

	@Query(nativeQuery = true, value = "select * from Consumer where FUEL_CARD_NUMBER = ? ")
	Consumer findByFuelCardNumber(int cardNumber);

	@Query(nativeQuery = true, value = "select * from Consumer where DRUGSTORE_CARD_NUMBER = ? ")
	Consumer findByDrugstoreCardNumber(int cardNumber);

	@Query(nativeQuery = true, value = "select * from Consumer where ID = ? ")
	Consumer findByIdConsumer(int id);

	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update Consumer " + "set name = :name, " + "document_number = :documentNumber, "
			+ "birth_date = :birthDate, " + "mobile_Phone_Number = :mobilePhoneNumber, "
			+ "residence_Phone_Number = :residencePhoneNumber, " + "phone_Number = :phoneNumber, " + "email = :email, "
			+ "street = :street, " + "number = :number, " + "city = :city, " + "country = :country, "
			+ "portal_Code = :portalCode, " + "food_Card_Number = :foodCardNumber, "
			+ "fuel_Card_Number = :fuelCardNumber, " + "drugstore_Card_Number = :drugstoreCardNumber "
			+ "where ID = :id ")
	void update(@Param("id") Integer id, @Param("name") String name, @Param("documentNumber") int documentNumber,
			@Param("birthDate") Date birthDate, @Param("mobilePhoneNumber") int mobilePhoneNumber,
			@Param("residencePhoneNumber") int residencePhoneNumber, @Param("phoneNumber") int phoneNumber,
			@Param("email") String email, @Param("street") String street, @Param("number") int number,
			@Param("city") String city, @Param("country") String country, @Param("portalCode") int portalCode,
			@Param("foodCardNumber") int foodCardNumber, @Param("fuelCardNumber") int fuelCardNumber,
			@Param("drugstoreCardNumber") int drugstoreCardNumber);

	@Query(nativeQuery = true, value = "select * from Consumer where ID <> :id and"
			+ " ((:food > 0 and (FOOD_CARD_NUMBER = :food or DRUGSTORE_CARD_NUMBER = :food or FUEL_CARD_NUMBER = :food)) or "
			+ " (:drug > 0 and (FOOD_CARD_NUMBER = :drug or DRUGSTORE_CARD_NUMBER = :drug or FUEL_CARD_NUMBER = :drug)) or "
			+ " (:fuel > 0 and (FOOD_CARD_NUMBER = :fuel or DRUGSTORE_CARD_NUMBER = :fuel or FUEL_CARD_NUMBER = :fuel))) ")
	List<Consumer> findByCardNumber(@Param("id") Integer id, @Param("food") Integer f, @Param("drug") Integer d,
			@Param("fuel") Integer u);

	@Query(nativeQuery = true, value = "select * from Consumer where "
			+ " FOOD_CARD_NUMBER = :cardNumber or DRUGSTORE_CARD_NUMBER = :cardNumber or FUEL_CARD_NUMBER = :cardNumber")
	List<Consumer> validateCardNumber(@Param("cardNumber") Integer id);
}
