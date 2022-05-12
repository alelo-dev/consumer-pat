package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.model.exception.CustomException;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.validator.EstablishmentValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static br.com.alelo.consumer.consumerpat.utils.types.CardAndEstablishmentType.DRUGSTORE;
import static br.com.alelo.consumer.consumerpat.utils.types.CardAndEstablishmentType.FOOD;
import static br.com.alelo.consumer.consumerpat.utils.types.ExceptionsType.ESTABLISHMENT_NOT_FOUND;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class EstablishmentServiceTest {

    @Mock
    private MessageService messageService;

    @Mock
    private EstablishmentRepository establishmentRepository;

    @Mock
    private EstablishmentValidator establishmentValidator;

    @InjectMocks
    private EstablishmentService establishmentService;

    private Establishment establishment;

    @BeforeEach
    public void setUp() {

        establishment = Establishment.builder().id(1L).name("test").type(DRUGSTORE).build();
    }

    @Test
    public void createEstablishment() {

        establishmentService.createEstablishment(establishment);

        verify(establishmentValidator, times(1)).accept(any(Establishment.class));
        verify(establishmentRepository, times(1)).save(any(Establishment.class));
    }

    @Test
    public void testGetOrCreateNewEstablishment() {

        when(establishmentRepository.findEstablishmentByNameAndType(anyString(), anyString())).thenReturn(empty());
        when(establishmentRepository.save(any(Establishment.class))).thenReturn(establishment);

        final Establishment response = establishmentService.getOrCreate(establishment);

        verify(establishmentValidator, times(2)).accept(any(Establishment.class));
        verify(establishmentRepository, times(1)).save(any(Establishment.class));
        assertEquals(1L, response.getId());
    }

    @Test
    public void testGetOrCreateExistingEstablishment() {

        final Long id = 2L;
        final Establishment establishmentForTest =
                Establishment.builder().id(id).name(establishment.getName()).type(establishment.getType()).build();
        when(establishmentRepository.findEstablishmentByNameAndType(anyString(), anyString())).thenReturn(ofNullable(establishment));

        assertEquals(id, establishmentForTest.getId());
        final Establishment response = establishmentService.getOrCreate(establishmentForTest);

        verify(establishmentValidator, times(1)).accept(any(Establishment.class));
        //como o estabelecimento já existe, retorna os dados do persistido e não salva o novo
        verify(establishmentRepository, times(0)).save(any(Establishment.class));
        assertEquals(1L, response.getId());
    }

    @Test
    void updateEstablishment() {

        final Long id = 1L;
        final Establishment establishmentForTest =
                Establishment.builder().id(id).name("test 2").type(FOOD).build();
        when(establishmentRepository.findById(eq(id))).thenReturn(ofNullable(establishment));

        assertEquals(FOOD, establishmentForTest.getType());
        establishmentService.updateEstablishment(establishmentForTest);

        verify(establishmentValidator, times(1)).accept(any(Establishment.class));
        final ArgumentCaptor<Establishment> argumentEstablishment = ArgumentCaptor.forClass(Establishment.class);
        verify(establishmentRepository, times(1)).save(argumentEstablishment.capture());
        assertEquals(establishmentForTest.getName(), argumentEstablishment.getValue().getName());
        assertEquals(establishment.getType(), argumentEstablishment.getValue().getType());
    }

    @Test
    void testFindAll() {

        when(establishmentRepository.findAll()).thenReturn(List.of(establishment));
        final List<Establishment> establishments = establishmentService.findAll();

        assertEquals(1, establishments.size());
    }

    @Test
    void testFindById() {

        final Long id = 1L;
        when(establishmentRepository.findById(eq(id))).thenReturn(ofNullable(establishment));
        final Establishment response = establishmentService.findById(id);

        assertEquals("test", response.getName());
    }

    @Test
    void testFindByIdNotFound() {

        final CustomException exceptionThatWasThrown = assertThrows(CustomException.class, () -> {
            final Long id = 1L;
            when(establishmentRepository.findById(eq(id))).thenReturn(empty());
            establishmentService.findById(id);
        });

        assertThat(exceptionThatWasThrown.getInternalErrorCode(), equalTo(ESTABLISHMENT_NOT_FOUND.getCode()));
    }

    @Test
    void findEstablishmentByNameAndType() {

        when(establishmentRepository.findEstablishmentByNameAndType(eq(establishment.getName()),
                eq(establishment.getType().name()))).thenReturn(ofNullable(establishment));
        final Optional<Establishment> response =
                establishmentService.findEstablishmentByNameAndType(establishment.getName(),
                        establishment.getType());

        assertTrue(response.isPresent());
    }
}