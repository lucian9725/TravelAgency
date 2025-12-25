package sda.academy.travelagency.Controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sda.academy.travelagency.Dto.LoginRequest;
import sda.academy.travelagency.Dto.LoginResponse;
import sda.academy.travelagency.Dto.RegisterRequest;
import sda.academy.travelagency.Entity.User;
import sda.academy.travelagency.Exception.user.UserAlreadyTakenException;
import sda.academy.travelagency.Security.UserDetailsImpl;
import sda.academy.travelagency.Service.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/auth-api")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody RegisterRequest registerRequest)
    {
        User existing = userService.findByEmail(registerRequest.getEmail());
        if (existing != null)
        {
            throw new UserAlreadyTakenException("Email is already in use");
        }
        userService.saveUser(registerRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/admin-register")
    public ResponseEntity<Void> registerAdmin(@RequestBody RegisterRequest registerRequest)
    {
        User existing = userService.findByEmail(registerRequest.getEmail());
        if (existing != null)
        {
            throw new UserAlreadyTakenException("Email is already in use");
        }
        userService.saveAdmin(registerRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @PostMapping("/signin")
    public ResponseEntity<LoginResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority()).toList();

        LoginResponse response = new LoginResponse();
        response.setFullName(userDetails.getUserNameInfo());// aici intoarcem USERNAME
        response.setEmail(userDetails.getUsername()); // intoarce EMAIL in loc de USERNAME!!!
        response.setRole(roles.get(0));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

}