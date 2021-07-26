package br.com.alelo.consumer.consumerpat.controller;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 25/07/2021 | 11:24
 */

import br.com.alelo.consumer.consumerpat.business.service.AuthBService;
import br.com.alelo.consumer.consumerpat.entity.AuthToken;
import br.com.alelo.consumer.consumerpat.model.dto.ApiDTO;
import br.com.alelo.consumer.consumerpat.util.Constants;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    private AuthBService authBService;

    @ApiOperation(value = "Gerar um novo token")
    @PostMapping(path = "/token")
    public ResponseEntity<ApiDTO<AuthToken>> generate(){
        ApiDTO<AuthToken> apiDTO = new ApiDTO<>(Constants.Codes.CODE_FAIL, Constants.Success.API_OK, this.authBService.save());
        return ResponseEntity.ok(apiDTO);
    }

    @ApiOperation(value = "Metodo usado internamente pela aplicação", hidden = true)
    @GetMapping(path = "/error/{type}")
    public ResponseEntity<ApiDTO<String>> error(@PathVariable(name = "type") String type){

        if(type.equalsIgnoreCase("MISSING_TOKEN")){
            ApiDTO<String> apiDTO = new ApiDTO<>(Constants.Codes.CODE_FAIL, Constants.Errors.AUTH_INVALID, "Missing header token");
            return new ResponseEntity<>(apiDTO, HttpStatus.UNAUTHORIZED);
        }else{
            ApiDTO<String> apiDTO = new ApiDTO<>(Constants.Codes.CODE_FAIL, Constants.Errors.AUTH_INVALID, "Token expired");
            return new ResponseEntity<>(apiDTO, HttpStatus.UNAUTHORIZED);
        }
    }

}
