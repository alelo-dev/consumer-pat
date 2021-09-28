package br.com.alelo.consumer.consumerpat.domain.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessage {

    ESTABELECIMENTO_NAO_PERMITIDO_PARA_CARTAO("Estabelecimento não permite o cartao utilizado."),
    SALDO_INSUFICIENTE("Saldo insuficiente para concluir a transação."),
    CARTAO_INEXISTENTE("Cartão não encontrado."),
    VALOR_MENOR_OU_IGUAL_ZERO("Valor da compra não pode ser menor ou igual a zero."),
    CONSUMIDOR_NAO_ENCONTRADO("Consumidor não encontrado"),
    PERMISSAO_ALTERACAO_SALDO_DO_CARTAO("Saldo do cartão não deve ser alterado na atualização cadastral.")
    ;

    private String message;

}
