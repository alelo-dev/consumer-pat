package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.User;
import br.com.alelo.consumer.consumerpat.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void initEach(){

        MockitoAnnotations.openMocks(this);
        user = new User("user1", "123");

    }

    @Test
    public void shouldCreateUser() {

        try {
            userService.saveUser(user);
        } catch(Exception e) {
            fail("Should not have thrown any exception. Exception: " + e.getMessage());
        }

    }

    @Test
    public void shouldThrowExceptionWhenUserAlreadyExists() {

        Mockito.when(userRepository.findByName(user.getName())).thenReturn(Optional.of(user));

        try {
            userService.saveUser(user);
            fail("Should have thrown an exception");
        } catch(Exception e) {
            assertThat(e.getMessage(), is("Username already exists"));
        }

    }

}