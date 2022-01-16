package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.dto.CardDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

//@JsonTypeName("drugstore_card")
@Getter
@Setter
@Entity
@DiscriminatorValue("DRUGSTORE_CARD")
public class DrugstoreCard extends Card {

    public DrugstoreCard(CardDTO c) {
        super.setCardType(c.getCardType());
        super.setNumber(c.getNumber());
        super.setBalance(c.getBalance() == null ? BigDecimal.ZERO : c.getBalance());
    }

    public DrugstoreCard() {
    }

    @Override
    public void subtractBalance(BigDecimal balance) {
        this.setBalance(getBalance().subtract(balance));
    }
}
