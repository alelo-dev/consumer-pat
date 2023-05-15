package br.com.alelo.consumer.consumerpat.auth;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import br.com.alelo.consumer.consumerpat.auth.execeptions.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("oauth")
@Transactional
@PreAuthorize("hasAuthority('USER') OR hasAuthority('ADMIN') OR hasAuthority('SUPER_ADMIN')")
public class AuthController {

	final private AuthService service;

	@Autowired
	public AuthController(AuthService service) {
		this.service = service;
	}

	@ApiOperation("Logout user, invalidating the token")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Successfully sent"),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 410, message = "Processing request error"),
		@ApiResponse(code = 404, message = "Not found")
	})
	@GetMapping("logout")
	public ResponseEntity<String> logout() throws AuthException {
		return service.logout();
	}
}