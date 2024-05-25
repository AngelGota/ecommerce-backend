package pro.angelogomez.ecommerce.backend.infrastructure.adapter;

import org.springframework.data.repository.CrudRepository;
import pro.angelogomez.ecommerce.backend.infrastructure.entity.UserEntity;

public interface IUserCrudRepository extends CrudRepository<UserEntity, Integer>{

}