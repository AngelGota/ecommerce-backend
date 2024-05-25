package pro.angelogomez.ecommerce.backend.domain.port;

import pro.angelogomez.ecommerce.backend.domain.model.Product;

public interface IProductRepository {
    Product save(Product product);
    Iterable<Product> findAll();
    Product findById(Integer id);
    void deleteById(Integer id);
}
