package id.co.metrodata.serverApp.services;

import id.co.metrodata.serverApp.models.Category;
import id.co.metrodata.serverApp.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category id not found!"));
    }

    public Category create(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category name is already exists!");
        }
        return categoryRepository.save(category);
    }

    public Category update(Long id, Category category) {
        getById(id);
        category.setId(id);
        return categoryRepository.save(category);
    }

    public Category delete(Long id) {
        Category category = getById(id);
        categoryRepository.delete(category);
        return category;
    }
}
