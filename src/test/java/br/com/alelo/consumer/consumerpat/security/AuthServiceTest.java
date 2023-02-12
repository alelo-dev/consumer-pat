package br.com.alelo.consumer.consumerpat.security;

import br.com.alelo.consumer.consumerpat.entity.User;
import br.com.alelo.consumer.consumerpat.repository.UserRepository;
import br.com.alelo.consumer.consumerpat.utils.AESUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void initEach() throws UnsupportedEncodingException, NoSuchPaddingException,
            IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        MockitoAnnotations.openMocks(this);

        String pass = "123";
        String encryptedPass = AESUtil.encryptText(pass);
        user = new User("user1", encryptedPass);
    }

    @Test
    public void shouldAuthorizeWhenValidUserAndPass() {

        String userName = "user1";
        String pass = "123";
        String userPass = userName + ":" + pass;
        String encodedUser = Base64.getEncoder().encodeToString(userPass.getBytes());
        String authorizationHeader = "Basic " + encodedUser;

        Mockito.when(userRepository.findByName(userName)).thenReturn(Optional.of(user));

        boolean authenticated = authService.validateBasicAuthentication(authorizationHeader);

        assertThat(authenticated, is(true));

    }

    @Test
    public void shouldNotAuthorizeWhenUserInvalid() {

        String userName = "user1";
        String pass = "123";
        String userPass = userName + ":" + pass;
        String encodedUser = Base64.getEncoder().encodeToString(userPass.getBytes());
        String authorizationHeader = "Basic " + encodedUser;

        Mockito.when(userRepository.findByName(userName)).thenReturn(Optional.ofNullable(null));

        boolean authenticated = authService.validateBasicAuthentication(authorizationHeader);

        assertThat(authenticated, is(false));

    }

    @Test
    public void shouldNotAuthorizeWhenUserValidAndPassInvalid() {

        String userName = "user1";
        String pass = "321";
        String userPass = userName + ":" + pass;
        String encodedUser = Base64.getEncoder().encodeToString(userPass.getBytes());
        String authorizationHeader = "Basic " + encodedUser;

        Mockito.when(userRepository.findByName(userName)).thenReturn(Optional.of(user));

        boolean authenticated = authService.validateBasicAuthentication(authorizationHeader);

        assertThat(authenticated, is(false));

    }

}
