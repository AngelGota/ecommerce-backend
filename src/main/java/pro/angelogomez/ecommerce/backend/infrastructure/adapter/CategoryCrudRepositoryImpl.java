package pro.angelogomez.ecommerce.backend.infrastructure.adapter;

import org.springframework.stereotype.Repository;
import pro.angelogomez.ecommerce.backend.domain.model.Category;
import pro.angelogomez.ecommerce.backend.domain.port.ICategoryRepository;
import pro.angelogomez.ecommerce.backend.infrastructure.mapper.CategoryMapper;

@Repository
public class CategoryCrudRepositoryImpl implements ICategoryRepository {
    private final ICategoryCrudRepository iCategoryCrudRepository;
    private final CategoryMapper categoryMapper;

    public CategoryCrudRepositoryImpl(ICategoryCrudRepository iCategoryCrudRepository, CategoryMapper categoryMapper) {
        this.iCategoryCrudRepository = iCategoryCrudRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Category save(Category category) {
        return categoryMapper.toCategory(iCategoryCrudRepository.save(categoryMapper.toCategoryEntity(category)));
    }

    @Override
    public Iterable<Category> findAll() {
        return categoryMapper.toCategories(iCategoryCrudRepository.findAll());
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.toCategory(iCategoryCrudRepository.findById(id).orElseThrow(
                () -> new RuntimeException("ID Category: " +id+ " not found")
        ));
    }

    @Override
    public void deleteById(Integer id) {
        iCategoryCrudRepository.findById(id).orElseThrow(
                () -> new RuntimeException("ID Category: " +id+ " not found")
        );
        iCategoryCrudRepository.deleteById(id);
    }
}
