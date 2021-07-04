package br.com.alelo.consumer.consumerpat.domain.consumer;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private long contactId;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @OneToOne(mappedBy = "contact")
    private Consumer consumer;

    @Builder
    public Contact(String phoneNumber, String email, Consumer consumer) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.consumer = consumer;
    }
}
