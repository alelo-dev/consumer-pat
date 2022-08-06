package br.com.alelo.consumer.consumerpat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "extract")
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "purchase_code")
    private String purchaseCode;

    @Column(name = "establishment_type")
    @Enumerated(EnumType.STRING)
    private Type establishmentType;

    private String establishmentName;

    @ElementCollection
    @CollectionTable(name = "products", joinColumns = @JoinColumn(name = "extract_id"))
    private List<String> products;

    @Column(name = "date_buy")
    private LocalDate dateBuy;

    @Column(name = "card_number")
    private String cardNumber;

    private BigDecimal amount;

}
