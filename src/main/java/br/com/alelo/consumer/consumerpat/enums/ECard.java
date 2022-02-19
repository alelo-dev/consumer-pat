package br.com.alelo.consumer.consumerpat.enums;

import br.com.alelo.consumer.consumerpat.entity.card.CardStrategyAlimentacao;
import br.com.alelo.consumer.consumerpat.entity.card.CardStrategyCombustivel;
import br.com.alelo.consumer.consumerpat.entity.card.CardStrategyDrugStore;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ECard {

    ALIMENTACAO(1L, CardStrategyAlimentacao.class),
    DRUGSTORE(2L, CardStrategyDrugStore.class),
    COMBUSTIVEL(3L, CardStrategyCombustivel.class);

    private final Long id;
    private final Class card;


public static ECard findById(Long id){

    return Arrays.stream(ECard.values())
            .filter(type -> type.getId().equals(id)).findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Código de cartao inexistente"));
}

}
