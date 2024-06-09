package pro.angelogomez.ecommerce.backend.infrastructure.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.angelogomez.ecommerce.backend.application.RegistrationService;
import pro.angelogomez.ecommerce.backend.domain.model.User;

@RestController
@RequestMapping("/api/v1/security")
@AllArgsConstructor
@Slf4j
public class RegistrationController {
    private final RegistrationService registrationService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        log.info("Clave recibida: {}", user.getPassword());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        log.info("Clave encriptada: {}", bCryptPasswordEncoder.encode(user.getPassword()));
        return new ResponseEntity<>(registrationService.register(user), HttpStatus.CREATED);
    }

}
