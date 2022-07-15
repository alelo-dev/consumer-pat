package br.com.alelo.consumer.consumerpat.model.entity;

import br.com.alelo.consumer.consumerpat.model.enums.ContactType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value", length = 45, nullable = false)
    private String value;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 15, nullable = false)
    private ContactType type;

    @ManyToOne
    @JoinColumn(name = "consumer_id", nullable = false)
    private Consumer consumer;

}
