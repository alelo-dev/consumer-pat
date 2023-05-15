package br.com.alelo.consumer.consumerpat;

import br.com.alelo.consumer.consumerpat.role.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	private UserRepository repository;

	@InjectMocks
	private UserServiceImp service;

	@Test
	public void itReturnsAnStringWhenTryingToRecoverThePassword() {
		String asd = service.recoveryPassword("asd");
		assertThat(asd, is(equalTo("some")));
	}

	@Test(expected = UserNotFoundException.class)
	public void itReturnsAnExceptionWhenTryingToFindAnUserByEmailAndTheUserIsNull() {

		when(repository.findOneByEmail(any())).thenReturn(null);

		service.findOneByEmail(any());
	}

	@Test
	public void itReturnAListOfAuthoritiesForAuthenticatedUser() {
		final Role ROLE_USER = new RoleDataBuilder().build();
		final Role ROLE_ADMIN = new RoleDataBuilder().build();
		final Role ROLE_SUPER_ADMIN = new RoleDataBuilder().build();

		User mockedUser = new UserDataBuilder(true)
			.withRole(ROLE_USER)
			.withRole(ROLE_ADMIN)
			.withRole(ROLE_SUPER_ADMIN)
			.build();

		when(repository.findOneByEmail(any())).thenReturn(mockedUser);

		UserDetails theUser = service.findOneByEmail(any());

		assertThat(theUser.getAuthorities().size(), is(equalTo(3)));
	}

	@Test
	public void itGetAnInstanceOfUserDetailsValuesAreNotNull() {
		final Role ROLE_USER = new RoleDataBuilder().build();
		final User mockedUser = new UserDataBuilder().withRole(ROLE_USER).build();

		when(repository.findOneByEmail(any())).thenReturn(mockedUser);

		UserDetails theUser = service.findOneByEmail(any());

		assertThat(theUser, is(instanceOf(UserDetails.class)));
		assertThat(theUser.getUsername(), is(notNullValue()));
		assertThat(theUser.getPassword(), is(notNullValue()));
		assertThat(theUser.getAuthorities(), is(notNullValue()));
	}

}