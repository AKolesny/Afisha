package by.it_academy.classifier_service.service;

import by.it_academy.classifier_service.dao.api.ICategoryDao;
import by.it_academy.classifier_service.dao.entity.Category;
import by.it_academy.classifier_service.dto.category.CategoryDto;
import by.it_academy.classifier_service.mappers.CategoryMapper;
import by.it_academy.classifier_service.service.api.ICategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class CategoryService implements ICategoryService {

    private final ICategoryDao dao;
    private final CategoryMapper mapper;

    public CategoryService(ICategoryDao dao, CategoryMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public Category save(CategoryDto dto) {
        Category category = this.mapper.convertToCategory(dto);

        this.dao.save(category);

        return category;
    }

    @Override
    public Page<Category> getAll(PageRequest pageRequest) {
        return this.dao.findAll(pageRequest);
    }

    @Override
    public Category get(UUID uuid) {
        return this.dao.findById(uuid)
                .orElseThrow(() -> {throw new EntityNotFoundException();
                });
    }
}
