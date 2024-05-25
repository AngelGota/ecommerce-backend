package pro.angelogomez.ecommerce.backend.domain.port;

import pro.angelogomez.ecommerce.backend.domain.model.User;

public interface IUserRepository {
    User save(User user);
    User findByEmail(String email);
    User findById(Integer id);
}
