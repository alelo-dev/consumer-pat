package br.com.alelo.consumer.consumerpat.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.alelo.consumer.consumerpat.dto.CardDTO;
import lombok.Data;

@Data
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 16)
    private String cardNumber;
    private CardType cardType;
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;

    @OneToMany(mappedBy = "card")
    private List<Extract> extracts;

    public Card() {

    }
    
    public Card(Long idConsumer, CardDTO dto) {
        this.id = dto.getId();
        this.balance = dto.getBalance();
        this.cardNumber = dto.getCardNumber();
        this.cardType = dto.getCardType();
        this.consumer = new Consumer(idConsumer);
    }
    
}
