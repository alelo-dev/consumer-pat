package br.com.alelo.consumer.consumerpat.enums;

import br.com.alelo.consumer.consumerpat.entity.card.CardAlimentacao;
import br.com.alelo.consumer.consumerpat.entity.card.CardCombustivel;
import br.com.alelo.consumer.consumerpat.entity.card.CardDrugStore;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ECard {

    ALIMENTACAO(1L, CardAlimentacao.class),
    DRUGSTORE(1L, CardDrugStore.class),
    COMBUSTIVEL(1L, CardCombustivel.class);

    private final Long id;
    private final Class card;


public static ECard findById(Long id){

    return Arrays.stream(ECard.values())
            .filter(type -> type.getId().equals(id)).findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Código de cartao inexistente"));
}

}
