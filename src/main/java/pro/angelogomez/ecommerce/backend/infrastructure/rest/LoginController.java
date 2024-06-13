package pro.angelogomez.ecommerce.backend.infrastructure.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pro.angelogomez.ecommerce.backend.infrastructure.dto.UserDTO;
import pro.angelogomez.ecommerce.backend.infrastructure.jwt.JWTGenerator;

@RestController // http://localhost:8085/api/v1/security
@RequestMapping("/api/v1/security")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@AllArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;

    @PostMapping("/login") // http://localhost:8085/api/v1/security/login
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.username(), userDTO.password())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.info("Rol del usuario logueado: {}", authentication.getAuthorities().stream().findFirst().get().toString());

        String token = jwtGenerator.getToken(userDTO.username());

        return new ResponseEntity<>("Usuario logueado correctamente. Your token is: "+ token, HttpStatus.OK);
    }
}
