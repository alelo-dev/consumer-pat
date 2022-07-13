package br.com.alelo.consumer.consumerpat.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

/**
 * @author renanravelli
 */

@Getter
@AllArgsConstructor
@ApiModel(value = "PageJsonResponse", description = "Informa\u00E7\u00F5es da pagina\u00E7\u00E3o.")
public class PageJsonResponse<T> {

  private List<T> conteudo;

  @ApiModelProperty(name = "pagina", value = "P\u00E1gina", position = 1)
  private int pagina;

  @ApiModelProperty(name = "tamanho", value = "Tamanho", position = 2)
  private int tamanho;

  @ApiModelProperty(name = "quantidadePagina", value = "Quantidade de p\u00E1gina", position = 3)
  private int quantidadePagina;

  @ApiModelProperty(name = "quantidadeItem", value = "Quantidade de item", position = 4)
  private long quantidadeItem;

  @ApiModelProperty(name = "quantidadeTotalItem", value = "Quantidade total de item", position = 5)
  private long quantidadeTotalItem;

  public static <S, T> PageJsonResponse<S> from(Page<T> paginacao, Function<T, S> mapper) {
    return new PageJsonResponse<S>(
        paginacao.getContent().stream().map(mapper).collect(Collectors.toList()),
        paginacao.getNumber(),
        paginacao.getSize(),
        paginacao.getTotalPages(),
        paginacao.getNumberOfElements(),
        paginacao.getTotalElements()
    );
  }

}
