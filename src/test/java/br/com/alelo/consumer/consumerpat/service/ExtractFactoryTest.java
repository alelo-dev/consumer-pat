package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.enums.CompanyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

public class ExtractFactoryTest {

    @Mock
    private ExtractStrategy strategy1;

    @Mock
    private ExtractStrategy strategy2;

    @Mock
    private ExtractStrategy strategy3;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindStrategyByCompanyId() {
        // Criando um conjunto de estratégias mock
        Set<ExtractStrategy> strategySet = new HashSet<>();
        strategySet.add(strategy1);
        strategySet.add(strategy2);
        strategySet.add(strategy3);

        // Configurando o comportamento dos mocks
        when(strategy1.getStrategyName()).thenReturn(CompanyType.FOOD);
        when(strategy2.getStrategyName()).thenReturn(CompanyType.DRUGSTORE);
        when(strategy3.getStrategyName()).thenReturn(CompanyType.FUEL);

        // Criando a instância da classe ExtractFactoryTest para teste
        ExtractFactory extractFactory = new ExtractFactory(strategySet);

        // Testando a busca por estratégia por tipo de empresa
        ExtractStrategy foundStrategy1 = extractFactory.findStrategyByCompanyId(CompanyType.FOOD);
        ExtractStrategy foundStrategy2 = extractFactory.findStrategyByCompanyId(CompanyType.DRUGSTORE);
        ExtractStrategy foundStrategy3 = extractFactory.findStrategyByCompanyId(CompanyType.FUEL);

        // Verificando se as estratégias retornadas são as mesmas que foram mockadas
        assertSame(strategy1, foundStrategy1);
        assertSame(strategy2, foundStrategy2);
        assertSame(strategy3, foundStrategy3);
    }
}

