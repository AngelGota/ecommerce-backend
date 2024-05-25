package pro.angelogomez.ecommerce.backend.infrastructure.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.angelogomez.ecommerce.backend.application.CategoryService;
import pro.angelogomez.ecommerce.backend.domain.model.Category;

@RestController // http://localhost:8085/api/v1/admin/categories
@RequestMapping("/api/v1/admin/categories")
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping // http://localhost:8085/api/v1/admin/categories
    public ResponseEntity<Category> save(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }

    @GetMapping // http://localhost:8085/api/v1/admin/categories
    public ResponseEntity<Iterable<Category>> findAll() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}") // http://localhost:8085/api/v1/admin/categories/1
    public ResponseEntity<Category> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}") // http://localhost:8085/api/v1/admin/categories/1
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Integer id) {
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
