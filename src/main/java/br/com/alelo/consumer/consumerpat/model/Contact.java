package br.com.alelo.consumer.consumerpat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.alelo.consumer.consumerpat.model.type.ContactType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "contact", uniqueConstraints = @UniqueConstraint(columnNames = { "value", "consumer_id" }))
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Contact {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Consumer consumer;

    @Column(nullable = false, length = 100)
    private String value;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private ContactType type;
    
    public void merge(final Contact contact) {
        this.value = contact.value;
        this.type  = contact.type;
    }

}
