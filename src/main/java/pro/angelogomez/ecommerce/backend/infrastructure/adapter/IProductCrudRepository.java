package pro.angelogomez.ecommerce.backend.infrastructure.adapter;

import org.springframework.data.repository.CrudRepository;
import pro.angelogomez.ecommerce.backend.infrastructure.entity.ProductEntity;

public interface IProductCrudRepository extends CrudRepository<ProductEntity, Integer> {
}
