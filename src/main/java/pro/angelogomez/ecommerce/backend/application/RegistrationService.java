package pro.angelogomez.ecommerce.backend.application;

import lombok.AllArgsConstructor;
import pro.angelogomez.ecommerce.backend.domain.model.User;
import pro.angelogomez.ecommerce.backend.domain.port.IUserRepository;

@AllArgsConstructor
public class RegistrationService {
    private final IUserRepository iUserRepository;

    public User register(User user) {
        return iUserRepository.save(user);
    }
}
