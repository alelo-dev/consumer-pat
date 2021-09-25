package br.com.alelo.consumer.consumerpat.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.alelo.consumer.consumerpat.dto.EstablishmentDto;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import org.junit.jupiter.api.Test;

public class EstablishmentMapperTest {

    private final EstablishmentMapper mapper = new EstablishmentMapper();

    @Test
    public void whenMapEstablishmentToEstablishmentDto_thenSuccess() {
        Long id = 1745L;
        String establishmentName = "establishmentName";

        Establishment establishment = Establishment.builder()
                .id(id)
                .establishmentName(establishmentName)
                .build();

        EstablishmentDto dto = mapper.mapModelToDto(establishment);

        assertEquals(id, dto.getId());
        assertEquals(establishmentName, dto.getEstablishmentName());
    }

    @Test
    public void whenMapEstablishmentDtoToEstablishment_thenSuccess() {
        Long id = 1745L;
        String establishmentName = "establishmentName";

        EstablishmentDto dto = EstablishmentDto.builder()
                .id(id)
                .establishmentName(establishmentName)
                .build();

        Establishment establishment = mapper.mapDtoToModel(dto);

        assertEquals(establishmentName, establishment.getEstablishmentName());
    }

    @Test
    public void whenUpdateEstablishmentFromDtoData_thenSuccess() {
        Long id = 1745L;
        String newEstablishmentName = "new name";

        Establishment establishment = Establishment.builder()
                .id(id)
                .establishmentName("establishment")
                .build();

        EstablishmentDto dto = EstablishmentDto.builder()
                .id(id)
                .establishmentName(newEstablishmentName)
                .build();

        establishment = mapper.updateModelTarget(establishment, dto);

        assertEquals(id, establishment.getId());
        assertEquals(newEstablishmentName, establishment.getEstablishmentName());
    }

}
