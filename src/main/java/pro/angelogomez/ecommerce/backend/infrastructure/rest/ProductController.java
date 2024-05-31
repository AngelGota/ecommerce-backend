package pro.angelogomez.ecommerce.backend.infrastructure.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.angelogomez.ecommerce.backend.application.ProductService;
import pro.angelogomez.ecommerce.backend.domain.model.Product;

import java.io.IOException;
import java.math.BigDecimal;

@RestController // http://localhost:8085/api/v1/admin/products
@RequestMapping("/api/v1/admin/products")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    private final ProductService productService;

    @PostMapping // http://localhost:8085/api/v1/admin/products
    public ResponseEntity<Product> save(@RequestParam("id") Integer id,
                                        @RequestParam("code") String code,
                                        @RequestParam("name") String name,
                                        @RequestParam("description") String description,
                                        @RequestParam("price") BigDecimal price,
                                        @RequestParam("urlImage") String urlImage,
                                        @RequestParam("userId") Integer userId,
                                        @RequestParam("categoryId") Integer categoryId,
                                        @RequestParam(value = "image", required = false) MultipartFile multipartFile) throws IOException {
        Product product = new Product();
        product.setId(id);
        product.setCode(code);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setUrlImage(urlImage);
        product.setUserId(userId);
        product.setCategoryId(categoryId);

        log.info("Product name: {}", product.getName()); // Info gracias a @Slf4j de Lombok
        return new ResponseEntity<>(productService.save(product, multipartFile), HttpStatus.CREATED);
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
