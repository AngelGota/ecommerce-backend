package pro.angelogomez.ecommerce.backend.infrastructure.adapter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pro.angelogomez.ecommerce.backend.domain.model.Product;
import pro.angelogomez.ecommerce.backend.domain.port.IProductRepository;
import pro.angelogomez.ecommerce.backend.infrastructure.mapper.ProductMapper;
@Repository
@AllArgsConstructor
public class ProductCrudRepositoryImpl implements IProductRepository {
    private final IProductCrudRepository iProductCrudRepository;
    private final ProductMapper productMapper;
    @Override
    public Product save(Product product) {
        return productMapper.toProduct(iProductCrudRepository.save(productMapper.toProductEntity(product)));
    }

    @Override
    public Iterable<Product> findAll() {
        return productMapper.toProducts(iProductCrudRepository.findAll());
    }

    @Override
    public Product findById(Integer id) {
        return productMapper.toProduct(iProductCrudRepository.findById(id).orElseThrow(
                () -> new RuntimeException("ID Product: " +id+ " not found")
        ));
    }

    @Override
    public void deleteById(Integer id) {
        iProductCrudRepository.findById(id).orElseThrow(
                () -> new RuntimeException("ID Product: " +id+ " not found")
        );
        iProductCrudRepository.deleteById(id);
    }
}
