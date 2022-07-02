package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    @Query(nativeQuery = true, value = "select * from Consumer")
    List<Consumer> getAllConsumersList();

    @Query(nativeQuery = true, value = "select * from Consumer where FOOD_CARD_NUMBER = ? ")
    Consumer findByFoodCardNumber(int cardNumber);

    @Query(nativeQuery = true, value = "select * from Consumer where FUEL_CARD_NUMBER = ? ")
    Consumer findByFuelCardNumber(int cardNumber);

    @Query(nativeQuery = true, value = "select * from Consumer where DRUGSTORE_NUMBER = ? ")
    Consumer findByDrugstoreNumber(int cardNumber);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update Consumer c set c.NAME = :name, c.DOCUMENT_NUMBER = :documentNumber, c.BIRTH_DATE = :birthDate, c.MOBILE_PHONE_NUMBER = :mobilePhoneNumber, c.RESIDENCE_PHONE_NUMBER = :residencePhoneNumber, c.PHONE_NUMBER = :phoneNumber, c.EMAIL = :email, c.STREET = :street, c.NUMBER = :number, c.CITY = :city, c.COUNTRY = :country, c.PORTAL_CODE = :portalCode, c.FOOD_CARD_NUMBER = :foodCardNumber, c.FUEL_CARD_NUMBER = :fuelCardNumber, c.DRUGSTORE_NUMBER = :drugstoreNumber where c.ID = :id")
    void update(@Param("id") Integer id, @Param("name") String name, @Param("documentNumber") int documentNumber, @Param("birthDate") Date birthDate, @Param("mobilePhoneNumber") int mobilePhoneNumber, @Param("residencePhoneNumber") int residencePhoneNumber, @Param("phoneNumber") int phoneNumber, @Param("email") String email, @Param("street") String street, @Param("number") int number, @Param("city") String city, @Param("country") String country, @Param("portalCode") int portalCode, @Param("foodCardNumber") int foodCardNumber, @Param("fuelCardNumber") int fuelCardNumber, @Param("drugstoreNumber") int drugstoreNumber);
}
