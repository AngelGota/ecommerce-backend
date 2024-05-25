package pro.angelogomez.ecommerce.backend.infrastructure.rest;

import org.springframework.web.bind.annotation.*;
import pro.angelogomez.ecommerce.backend.application.UserService;
import pro.angelogomez.ecommerce.backend.domain.model.User;

@RestController // http://localhost:8085/api/v1/users
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping // http://localhost:8085/api/v1/users
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/{id}") // http://localhost:8085/api/v1/users/1
    public User findById(@PathVariable Integer id) {
        return userService.findById(id);
    }
}
