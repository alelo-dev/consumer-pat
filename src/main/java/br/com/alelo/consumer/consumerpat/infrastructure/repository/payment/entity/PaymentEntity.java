package br.com.alelo.consumer.consumerpat.infrastructure.repository.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class PaymentEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @Embedded
    private EstablishmentEntity establishment;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "buy_date")
    private LocalDate buyDate;

    @Column(name = "card_number")
    private String cardNumber;

    private BigDecimal amount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}

