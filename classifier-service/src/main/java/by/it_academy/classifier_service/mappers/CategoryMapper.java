package by.it_academy.classifier_service.mappers;

import by.it_academy.classifier_service.dao.entity.Category;
import by.it_academy.classifier_service.dto.api.IMyPage;
import by.it_academy.classifier_service.dto.category.CategoryDto;
import by.it_academy.classifier_service.dto.category.CategoryOutDto;
import by.it_academy.classifier_service.dto.page.MyPage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CategoryMapper {

    public CategoryOutDto convertToDto(Category category) {
        CategoryOutDto dto = new CategoryOutDto();

        dto.setUuid(category.getUuid());
        dto.setDtCreate(category.getDtCreate());
        dto.setDtUpdate(category.getDtUpdate());
        dto.setTitle(category.getTitle());

        return dto;
    }

    public Category convertToCategory(CategoryDto dto) {
        Category category = new Category();

        category.setUuid(UUID.randomUUID());
        category.setDtCreate(LocalDateTime.now());
        category.setDtUpdate(category.getDtUpdate());
        category.setTitle(dto.getTitle());

        return category;
    }

    public IMyPage<CategoryOutDto> convertToMyPage(Page<Category> page) {
        MyPage<CategoryOutDto> myPage = new MyPage<>();

        myPage.setNumber(page.getNumber());
        myPage.setSize(page.getSize());
        myPage.setTotalPages(page.getTotalPages());
        myPage.setTotalElements(page.getTotalElements());
        myPage.setFirst(page.isFirst());
        myPage.setNumberOfElements(page.getNumberOfElements());
        myPage.setLast(page.isLast());

        List<CategoryOutDto> content = new ArrayList<>();
        for (Category category : page.getContent()) {
            content.add(convertToDto(category));
        }

        myPage.setContent(content);

        return myPage;
    }
}
