package br.com.alelo.consumer.consumerpat.factory.components.impls;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.factory.components.interfaces.EstablishmentComponent;
import org.springframework.stereotype.Component;

@Component
public class DrugstoreComponentImpl implements EstablishmentComponent {
    @Override
    public void debit(BuyDTO buy) {

    }
}
