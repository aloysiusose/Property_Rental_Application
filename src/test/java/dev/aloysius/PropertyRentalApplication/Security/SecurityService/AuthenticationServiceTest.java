package dev.aloysius.PropertyRentalApplication.Security.SecurityService;

import dev.aloysius.PropertyRentalApplication.Exceptions.UserAlreadyExistException;
import dev.aloysius.PropertyRentalApplication.Models.Users.AppUsers;
import dev.aloysius.PropertyRentalApplication.Models.Users.AuthenticationRequest;
import dev.aloysius.PropertyRentalApplication.Models.Users.UserCategory;
import dev.aloysius.PropertyRentalApplication.Repository.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {
    @Mock
    private AppUserRepository appUserRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtEncoder encoder;
    @Mock
    private AuthenticationProvider authenticationProvider;
    @InjectMocks
    private AuthenticationService authenticationService;

//    @BeforeEach
//    void setUp() {
//        appUserRepository = mock(AppUserRepository.class);
//        passwordEncoder = mock(PasswordEncoder.class);
//        encoder = mock(JwtEncoder.class);
//        authenticationProvider = mock(AuthenticationProvider.class);
//        authenticationService = new AuthenticationService(appUserRepository,passwordEncoder,encoder, authenticationProvider);
//
//    }

    @Test
    void createUsers() throws UserAlreadyExistException {
        AppUsers appUsers = new AppUsers();
        appUsers.setFirstName("Aloysius"); appUsers.setLastName("Oseghale"); appUsers.setEmail("icangetbetter");
        appUsers.setUserPassword("12345"); appUsers.setUserCategory(UserCategory.BROKER);
       // when(passwordEncoder.encode(appUsers.getUserPassword())).thenReturn(passwordEncoder.encode("12345"));

        authenticationService.createUsers(appUsers);
        ArgumentCaptor<AppUsers> user = ArgumentCaptor.forClass(AppUsers.class);
        verify(appUserRepository, times(1)).save(user.capture());
        AppUsers value = user.getValue();
        assertEquals(appUsers, value);

    }

    @Test
    void authenticateUser() {
        AuthenticationRequest request = new AuthenticationRequest();
        request.setUsername("");
        request.setPassword("12345");

        Authentication auth = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

        given(authenticationProvider.authenticate(auth)).willReturn(auth);

        authenticationService.authenticateUser(request);
        Mockito.verify(authenticationProvider, times(1)).authenticate(new UsernamePasswordAuthenticationToken(request
                .getUsername(), request.getPassword()));

    }
}