package br.com.alelo.consumer.consumerpat.respository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    /**
     * @return
     */
    @Query(nativeQuery = true, value = "select * from Consumer")
    List<Consumer> getAllConsumersList();


    /**
     * @param cardNumber
     * @return
     */
    @Query(nativeQuery = true, value = "select * from Consumer where FOOD_CARD_NUMBER = ? ")
    Consumer findByFoodCardNumber(int cardNumber);

    /**
     * @param cardNumber
     * @return
     */
    @Query(nativeQuery = true, value = "select * from Consumer where FUEL_CARD_NUMBER = ? ")
    Consumer findByFuelCardNumber(int cardNumber);

    /**
     * @param cardNumber
     * @return
     */
    @Query(nativeQuery = true, value = "select * from Consumer where DRUGSTORE_NUMBER = ? ")
    Consumer findByDrugstoreNumber(int cardNumber);
    
    /**
     * @param name
     * @param documentNumber
     * @param birthDate
     * @param mobilePhoneNumber
     * @param residencePhoneNumber
     * @param phoneNumber
     * @param email
     * @param street
     * @param number
     * @param city
     * @param country
     * @param portalCode
     * @param id
     */
    @Query("update Consumer u set u.name = ?1 ,"
    		+ "u.documentNumber = ?2 ,"
    		+ "u.birthDate = ?3 ,"
    		+ "u.mobilePhoneNumber = ?4 ,"
    		+ "u.residencePhoneNumber = ?5 ,"
    		+ "u.phoneNumber = ?6 ,"
    		+ "u.email = ?7 ,"
    		+ "u.street = ?8 ,"
    		+ "u.number = ?9 ,"
    		+ "u.city = ?10 ,"
    		+ "u.country = ?11 ,"
    		+ "u.portalCode = ?12 ,"
    		+ "where u.id = ?13")
    void updateConsumer(String name, 
    		int documentNumber, 
    		Date birthDate, 
    		int mobilePhoneNumber, 
    		int residencePhoneNumber,
    		int phoneNumber, 
    		String email, 
    		String street, 
    		int number, 
    		String city, 
    		String country, 
    		int portalCode, 
    		Integer id);
    
    
    /* (non-Javadoc)
     * @see org.springframework.data.repository.PagingAndSortingRepository#findAll(org.springframework.data.domain.Pageable)
     */
    Page<Consumer> findAll(Pageable pageable);
}
