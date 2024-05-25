package pro.angelogomez.ecommerce.backend.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.angelogomez.ecommerce.backend.application.CategoryService;
import pro.angelogomez.ecommerce.backend.application.ProductService;
import pro.angelogomez.ecommerce.backend.application.UserService;
import pro.angelogomez.ecommerce.backend.domain.port.ICategoryRepository;
import pro.angelogomez.ecommerce.backend.domain.port.IProductRepository;
import pro.angelogomez.ecommerce.backend.domain.port.IUserRepository;

@Configuration
public class BeanConfiguration {
    @Bean
    public UserService userService(IUserRepository iUserRepository){
        return new UserService(iUserRepository);
    }
    @Bean
    public CategoryService categoryService(ICategoryRepository iCategoryRepository){
        return new CategoryService(iCategoryRepository);
    }
    @Bean
    public ProductService productService(IProductRepository iProductRepository){
        return new ProductService(iProductRepository);
    }
}
