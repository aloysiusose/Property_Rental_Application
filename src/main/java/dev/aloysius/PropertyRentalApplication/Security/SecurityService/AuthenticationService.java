package dev.aloysius.PropertyRentalApplication.Security.SecurityService;

import dev.aloysius.PropertyRentalApplication.Exceptions.UserAlreadyExistException;
import dev.aloysius.PropertyRentalApplication.Models.Users.AppUsers;
import dev.aloysius.PropertyRentalApplication.Models.Users.AuthenticationRequest;
import dev.aloysius.PropertyRentalApplication.Models.Users.UserCategory;
import dev.aloysius.PropertyRentalApplication.Repository.AppUserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {
    
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;
    private final AuthenticationProvider authenticationProvider;

    public AuthenticationService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder,
                                 JwtEncoder jwtEncoder, AuthenticationProvider authenticationProvider) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtEncoder = jwtEncoder;
        this.authenticationProvider = authenticationProvider;
    }
    public void createUsers(AppUsers appUsers) throws UserAlreadyExistException {
        if(isPresent(appUsers)){
            throw  new UserAlreadyExistException("User with email : %s already exist".formatted(appUsers.getEmail()));
        }
        appUsers.setUserPassword(passwordEncoder.encode(appUsers.getUserPassword()));
        appUserRepository.save(appUsers);
    }

    private boolean isPresent(AppUsers appUsers) {
        return appUserRepository.findByEmail(appUsers.getEmail()).isPresent();
    }

    public String authenticateUser(AuthenticationRequest authRequest){
        Authentication authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        SecurityContext newContext = SecurityContextHolder.createEmptyContext();
        newContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(newContext);
        return generateToken(authentication);
    }
    
    private String generateToken(Authentication authentication){
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .subject(authentication.getName())
                .expiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                .claim("scope", scope)
                .build();
        
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    public void switchUserToBroker() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        AppUsers appUsers = appUserRepository.findByEmail(name).get();
        appUsers.setUserCategory(UserCategory.BROKER);

        appUserRepository.save(appUsers);


    }
    public void switchUserToBuyer() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        AppUsers appUsers = appUserRepository.findByEmail(name).get();
        appUsers.setUserCategory(UserCategory.BUYER);
        appUserRepository.save(appUsers);


    }
}
