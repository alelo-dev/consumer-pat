package br.com.alelo.consumer.consumerpat.security;

import br.com.alelo.consumer.consumerpat.entity.User;
import br.com.alelo.consumer.consumerpat.repository.UserRepository;
import br.com.alelo.consumer.consumerpat.utils.AESUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

@Log4j2
@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public boolean validateBasicAuthentication(String basicAuthHeaderValue) {

        if (basicAuthHeaderValue != null && basicAuthHeaderValue.toLowerCase().startsWith("basic")) {
            // Authorization: Basic base64credentials
            String base64Credentials = basicAuthHeaderValue.substring("Basic".length()).trim();
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoded, StandardCharsets.UTF_8);
            // credentials = username:password
            final String[] values = credentials.split(":", 2);

            if (values.length == 2) {
                String username = values[0];
                String password = values[1];

                try {
                    Optional<User> user = userRepository.findByName(username);
                    if (user.isPresent()) {
                        String encryptPassword = AESUtil.encryptText(password);
                        if (user.get().getPassword().equals(encryptPassword)) {
                            return true;
                        }
                    }
                    return false;
                }catch (Exception e) {
                    log.error("Error while validating user", e);
                    return false;
                }
            }
        }
        return false;

    }

}