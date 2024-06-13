package pro.angelogomez.ecommerce.backend.infrastructure.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.angelogomez.ecommerce.backend.application.ProductService;
import pro.angelogomez.ecommerce.backend.domain.model.Product;

@RestController
@RequestMapping("/api/v1/home") // http://localhost:8085/api/v1/home
@AllArgsConstructor
public class HomeController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }
}
