package br.com.alelo.consumer.consumerpat.dataprovider.jpa.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "consumer", uniqueConstraints = @UniqueConstraint(columnNames = "consumerCode"))
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ConsumerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consumer_id")
    private Long id;

    @Column(length = 36, nullable = false)
    private String consumerCode;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(length = 14, nullable = false)
    private String document;

    @Column(nullable = false)
    private LocalDate birthDate;

    @OneToOne(mappedBy = "consumer")
    private ContactEntity contact;

    @OneToOne(mappedBy = "consumer")
    private AddressEntity address;

    @OneToMany(mappedBy = "consumer")
    private Set<CardEntity> cards;

    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime creationDate;

    @LastModifiedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updateDate;
}
