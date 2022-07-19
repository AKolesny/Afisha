package by.it_academy.classifier_service.service.api;

import by.it_academy.classifier_service.dao.entity.Category;
import by.it_academy.classifier_service.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface ICategoryService {

    void save(CategoryDto categoryDto);

    Page<Category> getAll(PageRequest pageRequest);

    Category get(UUID uuid);
}
