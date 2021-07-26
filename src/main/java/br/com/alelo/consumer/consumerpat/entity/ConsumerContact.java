package br.com.alelo.consumer.consumerpat.entity;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 24/07/2021 | 13:44
 */

import br.com.alelo.consumer.consumerpat.model.enums.ContactTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class ConsumerContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private ContactTypeEnum type;
    private String value;
    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;
    private LocalDateTime createdAt;

    public ConsumerContact(ContactTypeEnum type, String value) {
        this.type = type;
        this.value = value;
        this.createdAt = LocalDateTime.now();
    }

    public ConsumerContact(Integer id, ContactTypeEnum type, String value, LocalDateTime createdAt) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.createdAt = createdAt;
    }

}
