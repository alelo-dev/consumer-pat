package br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity;

import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Address;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Contact;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import org.springframework.beans.BeanUtils;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;
    private String name;
    private String documentNumber;
    private LocalDate birthDate;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressEntity address;

    @OneToOne(cascade = CascadeType.ALL)
    private ContactEntity contact;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public void changeCostumer(final Customer customer) {
        this.name = customer.getName();
        this.documentNumber = customer.getDocumentNumber();
        this.birthDate = customer.getBirthDate();
        this.addAddress(customer.getAddress());
        this.addContact(customer.getContact());
    }

    public void addContact(final Contact contact) {
        if (contact != null) {
            var  contactEntity = new ContactEntity();
            BeanUtils.copyProperties(contact, contactEntity);
            this.contact = contactEntity;
        }
    }

    public void addAddress(final Address address) {
        if (address != null) {
            var  addressEntity = new AddressEntity();
            BeanUtils.copyProperties(address, addressEntity);
            this.address = addressEntity;
        }
    }
}
