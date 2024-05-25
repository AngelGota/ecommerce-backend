package pro.angelogomez.ecommerce.backend.infrastructure.adapter;

import org.springframework.data.repository.CrudRepository;
import pro.angelogomez.ecommerce.backend.infrastructure.entity.CategoryEntity;

public interface ICategoryCrudRepository extends CrudRepository<CategoryEntity, Integer>{

}
