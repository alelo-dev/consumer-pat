package br.com.alelo.consumer.consumerpat.resource;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/consumers")
public interface ConsumerResource {

    String RETORNA_SUCESSO       = "Retorna sucesso";
    String OBJETO_NAO_ENCONTRADO = "Foi uma exceção de objeto não encontrado";
    String FOI_UM_ERRO_INTERNO   = "Foi gerado um erro interno";
    String EXCECAO_BAD_REQUEST   = "Retorna uma exceção Bad Request";

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = RETORNA_SUCESSO),
            @ApiResponse(code = 404, message = OBJETO_NAO_ENCONTRADO),
            @ApiResponse(code = 500, message = FOI_UM_ERRO_INTERNO)
    })
    @GetMapping(value = "/find/{id}")
    ResponseEntity<Consumer> findById(@PathVariable Integer id);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = RETORNA_SUCESSO),
            @ApiResponse(code = 400, message = EXCECAO_BAD_REQUEST),
            @ApiResponse(code = 500, message = FOI_UM_ERRO_INTERNO)
    })
    @GetMapping(value = "/list")
    ResponseEntity<Page<Consumer>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction);

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = RETORNA_SUCESSO),
            @ApiResponse(code = 400, message = EXCECAO_BAD_REQUEST),
            @ApiResponse(code = 500, message = FOI_UM_ERRO_INTERNO)
    })
    @PostMapping(value = "/create")
    ResponseEntity<Consumer> create(@RequestBody Consumer obj);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = RETORNA_SUCESSO),
            @ApiResponse(code = 400, message = EXCECAO_BAD_REQUEST),
            @ApiResponse(code = 404, message = OBJETO_NAO_ENCONTRADO),
            @ApiResponse(code = 500, message = FOI_UM_ERRO_INTERNO)
    })
    @PutMapping(value = "/update/{id}")
    ResponseEntity<Consumer> update(@PathVariable Integer id, @RequestBody Consumer obj);

}
