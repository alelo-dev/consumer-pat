package br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contact")
public class ContactEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;
    private String mobilePhoneNumber;
    private String residencePhoneNumber;
    private String email;
}
