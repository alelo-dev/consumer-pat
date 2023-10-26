package br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;
    private String street;
    private String number;
    private String city;
    private String country;
    private String postalCode;
}