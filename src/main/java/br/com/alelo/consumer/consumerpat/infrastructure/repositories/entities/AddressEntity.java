package br.com.alelo.consumer.consumerpat.infrastructure.repositories.entities;

import br.com.alelo.consumer.consumerpat.domain.entities.Address;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "address")
@NoArgsConstructor
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String street;
    private int number;
    private String city;
    private String country;
    @Column(name = "portal_code")
    private String portalCode;

    @OneToOne
    @JoinColumn(name = "consumer_id")
    private ConsumerEntity consumer;

    public Address toModel() {
        val address = new Address();
        BeanUtils.copyProperties(this, address);

        return address;
    }
}
