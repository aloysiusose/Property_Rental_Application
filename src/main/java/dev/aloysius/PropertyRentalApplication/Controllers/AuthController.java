package dev.aloysius.PropertyRentalApplication.Controllers;

import dev.aloysius.PropertyRentalApplication.Exceptions.UserAlreadyExistException;
import dev.aloysius.PropertyRentalApplication.Models.Users.AppUsers;
import dev.aloysius.PropertyRentalApplication.Models.Users.AuthenticationRequest;
import dev.aloysius.PropertyRentalApplication.Security.SecurityService.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/auth/")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public void createUser(@RequestBody AppUsers appUsers) throws UserAlreadyExistException {
        authenticationService.createUsers(appUsers);
    }
    @PostMapping("/login")
    public String userLogin(@RequestBody AuthenticationRequest authenticationRequest){
        return authenticationService.authenticateUser(authenticationRequest);
    }

    @PutMapping("/broker")
    public String switchToSeller(){
        authenticationService.switchUserToBroker();
        return "you can now start selling";
    }
    @PutMapping("/buyer")
    public String switchToBuyer(){
        authenticationService.switchUserToBuyer();
        return "you can now start selling";
    }
    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
