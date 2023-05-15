package br.com.alelo.consumer.consumerpat.auth;

import br.com.alelo.consumer.consumerpat.auth.execeptions.AuthException;
import br.com.alelo.consumer.consumerpat.auth.execeptions.AuthLogoutException;
import br.com.alelo.consumer.consumerpat.resource.DefaultApiResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.OK;

@Service(value = "authService")
public class AuthServiceImp implements AuthService {

	final private DefaultTokenServices tokenServices;
	final private DefaultApiResponse response;

	@Autowired
	public AuthServiceImp(DefaultTokenServices tokenServices, DefaultApiResponse response) {
		this.tokenServices = tokenServices;
		this.response = response;
	}

	@Override
	public ResponseEntity<String> logout() throws AuthException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
		boolean revokedToken = tokenServices.revokeToken(details.getTokenValue());
		if (revokedToken) {
			JSONObject user = new JSONObject(auth.getName());
			return response.ok("bye " + user.get("name") + "!");
		} else {
			throw new AuthLogoutException();
		}
	}
}