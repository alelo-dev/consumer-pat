package br.com.alelo.consumer.consumerpat.api.exceptionhandler;

import org.springframework.http.HttpStatus;

public enum ExceptionType {

	ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND,"/entidade-nao-encontrada", "Entidade não encontrada"),
	ENTITY_EXISTS(HttpStatus.BAD_REQUEST,"/entidade-ja-existe","Entidade já cadastrada na base de dados"),
    INVALID_BODY(HttpStatus.BAD_REQUEST,"/corpo-mensagem-invalido", "Problema no corpo do objeto."),
	EXCEEDED_VALUE(HttpStatus.BAD_REQUEST,"/limite-excedido", "Valor limite excedido. Não foi possível realizar a compra!"); 

    private String title;
    private String path;
    private HttpStatus status;

    ExceptionType(HttpStatus status, String path, String title) {
        this.status = status;
        this.path = "https://testealelo" + path;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getPath() {
        return path;
    }

    public HttpStatus getStatus() {
        return status;
    }
	
}
