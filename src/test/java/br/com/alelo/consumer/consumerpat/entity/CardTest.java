package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CardTest {

    @Test
    public void adicionaSaldo() throws Exception {
        Card card = new Card(666,CardType.FOOD);
        card.deposit(100);
        Assert.isTrue(card.getBalance().compareTo(new BigDecimal(100)) == 0, "Deve conter 100 de saldo");
        Assert.isTrue(card.getBalance().doubleValue() == 100, "Deve conter 100 de saldo");
    }

    @Test
    public void criaItemExtratoCartao() throws Exception {
        Card card = new Card(666,CardType.FOOD);
        card.deposit(666);
        Assert.isTrue(card.getCardHistory().size() == 1, "Deve conter um item em historico do cartao");
        Assert.isTrue(card.getCardHistory().get(0).getValue().doubleValue() == 666, "Deve conter 666 no unico registro de historico");
    }

    @Test()
    public void erroAoAdicionarSaldoNegativo() throws Exception {
        Card card = new Card(666,CardType.FOOD);
        assertThrows(
                Exception.class,
                () -> card.deposit(-600),
                "Não é permitido adicionar saldo negativo a conta"
        );
    }


    @Test()
    public void compraNormal() throws Exception {
        Card card = new Card(666,CardType.DRUG);
        card.deposit(100);
        card.buy(new BuyDTO("","",new BigDecimal(99),2));
        Assert.isTrue(card.getCardHistory().size() == 2, "Deve conter 2 item em historico do cartao");
        Assert.isTrue(card.getBalance().doubleValue() == 1, "Deve conter 1 de saldo");
    }


    @Test()
    public void validaTipoDeCartao() throws Exception {
        Card card = new Card(666,CardType.FOOD);
        card.deposit(100);
        assertThrows(
                Exception.class,
                () -> card.buy(new BuyDTO("","",new BigDecimal(99),2)),
                "Não é possivel realizar compras nesse estabelecimento com esse tipo de cartão"
        );
    }

    @Test()
    public void validaSaldo() throws Exception {
        Card card = new Card(666,CardType.FOOD);
        card.deposit(100);
        assertThrows(
                Exception.class,
                () -> card.buy(new BuyDTO("","",new BigDecimal(500),1)),
                "Saldo insuficiente"
        );
    }

    @Test()
    public void validaDiscontoComida() throws Exception {
        Card card = new Card(666,CardType.FOOD);
        card.deposit(100);
        card.buy(new BuyDTO("","",new BigDecimal(100),1));
        Assert.isTrue(card.getCardHistory().size() == 2, "Deve conter 2 item em historico do cartao");
        Assert.isTrue(card.getBalance().doubleValue() == 10, "Deve conter 10 de saldo");
    }

    @Test()
    public void validaAcrecimoCombustivel() throws Exception {
        Card card = new Card(666,CardType.FUEL);
        card.deposit(100);
        card.buy(new BuyDTO("","",new BigDecimal(50),3));
        Assert.isTrue(card.getCardHistory().size() == 2, "Deve conter 2 item em historico do cartao");
        Assert.isTrue(card.getBalance().doubleValue() == 32.5, "Deve conter 32.5 de saldo");
    }
}
