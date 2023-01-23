package br.com.alelo.consumer.consumerpat.respository;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
