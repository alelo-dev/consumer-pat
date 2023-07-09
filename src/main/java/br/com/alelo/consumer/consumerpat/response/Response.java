package br.com.alelo.consumer.consumerpat.response;

import br.com.alelo.consumer.consumerpat.dto.ResponseMensageDTO;
import org.springframework.http.ResponseEntity;

public interface Response {

    default ResponseEntity<?> body(Integer status, String mensagem) {
        return ResponseEntity.status(status).body(new ResponseMensageDTO(status, mensagem));
    }

}
