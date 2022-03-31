package br.com.alelo.consumer.consumerpat.factory;

import br.com.alelo.consumer.consumerpat.entity.enums.TypeEnum;
import br.com.alelo.consumer.consumerpat.factory.components.impls.DrugstoreComponentImpl;
import br.com.alelo.consumer.consumerpat.factory.components.impls.FoodComponentImpl;
import br.com.alelo.consumer.consumerpat.factory.components.impls.FuelComponentImpl;
import br.com.alelo.consumer.consumerpat.factory.components.interfaces.EstablishmentComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class EstablishmentFactory {
    @Autowired
    private DrugstoreComponentImpl drugstoreComponent;

    @Autowired
    private FoodComponentImpl foodComponent;

    @Autowired
    private FuelComponentImpl fuelComponent;

    private static final Map<TypeEnum, EstablishmentComponent> mapEstablishment = new HashMap<>();

    @PostConstruct
    private Map<TypeEnum, EstablishmentComponent> getMap() {
        mapEstablishment.put(TypeEnum.DRUGSTORE, drugstoreComponent);
        mapEstablishment.put(TypeEnum.FOOD, foodComponent);
        mapEstablishment.put(TypeEnum.FUEL, fuelComponent);

        return mapEstablishment;
    }

    public static EstablishmentComponent getInstance(TypeEnum type) {
        return Optional.ofNullable(mapEstablishment.get(type))
                .orElseThrow(() -> new IllegalArgumentException("Invalid type."));
    }
}
