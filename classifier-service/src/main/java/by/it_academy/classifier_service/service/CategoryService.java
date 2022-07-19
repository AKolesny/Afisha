package by.it_academy.classifier_service.service;

import by.it_academy.classifier_service.dao.api.ICategoryDao;
import by.it_academy.classifier_service.dao.entity.Category;
import by.it_academy.classifier_service.dto.CategoryDto;
import by.it_academy.classifier_service.service.api.ICategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CategoryService implements ICategoryService {

    ICategoryDao dao;

    public CategoryService(ICategoryDao dao) {
        this.dao = dao;
    }

    @Override
    public void save(CategoryDto categoryDto) {
        Category category = new Category();
        category.setUuid(UUID.randomUUID());
        category.setDtCreate(LocalDateTime.now());
        category.setDtUpdate(category.getDtUpdate());
        category.setTitle(categoryDto.getTitle());

        this.dao.save(category);
    }

    @Override
    public Page<Category> getAll(PageRequest pageRequest) {
        return this.dao.findAll(pageRequest);
    }

    @Override
    public Category get(UUID uuid) {
        return this.dao.findById(uuid)
                .orElseThrow(() -> {throw new IllegalArgumentException("Не нашли такой Category");
                });
    }
}
