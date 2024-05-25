package pro.angelogomez.ecommerce.backend.infrastructure.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.angelogomez.ecommerce.backend.application.ProductService;
import pro.angelogomez.ecommerce.backend.domain.model.Product;

@RestController // http://localhost:8085/api/v1/admin/products
@RequestMapping("/api/v1/admin/products")
@Slf4j
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping // http://localhost:8085/api/v1/admin/products
    public ResponseEntity<Product> save(@RequestBody Product product) {
        log.info("Product name: {}", product.getName()); // Info gracias a @Slf4j de Lombok
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }
    @GetMapping // http://localhost:8085/api/v1/admin/products
    public ResponseEntity<Iterable<Product>> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}") // http://localhost:8085/api/v1/admin/products/1
    public ResponseEntity<Product> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}") // http://localhost:8085/api/v1/admin/products/1
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Integer id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
