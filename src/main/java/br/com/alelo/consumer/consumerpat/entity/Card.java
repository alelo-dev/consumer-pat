package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.enums.CardType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "cardType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DrugstoreCard.class, name = "DRUGSTORE_CARD"),
        @JsonSubTypes.Type(value = FoodCard.class, name = "FOOD_CARD"),
        @JsonSubTypes.Type(value = FuelCard.class, name = "FUEL_CARD")})
@Getter
@Setter
@Entity(name = "cards")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "cardType", discriminatorType = DiscriminatorType.STRING)
public abstract class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "balance", columnDefinition = "Decimal(10,2)")
    @ColumnDefault("0")
    @Setter(value = AccessLevel.PROTECTED)
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(columnDefinition = "varchar(16)")
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(name = "cardType", insertable = false, updatable = false)
    private CardType cardType;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Extract> extract;

    public void addBalance(final BigDecimal balance) {
        this.balance = this.balance.add(balance);
    }

    public abstract void subtractBalance(final BigDecimal balance);
}
