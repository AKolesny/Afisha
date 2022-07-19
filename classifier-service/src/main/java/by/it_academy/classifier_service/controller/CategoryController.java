package by.it_academy.classifier_service.controller;

import by.it_academy.classifier_service.dao.entity.Category;
import by.it_academy.classifier_service.dto.CategoryDto;
import by.it_academy.classifier_service.dto.IMyPage;
import by.it_academy.classifier_service.dto.MyPage;
import by.it_academy.classifier_service.service.api.ICategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/classifier/concert/category")
public class CategoryController {

    private final ICategoryService service;

    public CategoryController(ICategoryService service) {
        this.service = service;
    }

    @PostMapping
    public void save(@RequestBody CategoryDto category) {
        this.service.save(category);
    }

    @GetMapping("/{uuid}")
    public Category get(@PathVariable UUID uuid) {
        return this.service.get(uuid);
    }

    @GetMapping
    public IMyPage<Category> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                          @RequestParam(value = "size", defaultValue = "25") Integer size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Category> categories = this.service.getAll(pageRequest);

        return new MyPage<>(categories.getNumber(), categories.getSize(),
                categories.getTotalPages(), categories.getTotalElements(), categories.isFirst(),
                categories.getNumberOfElements(), categories.isLast(), categories.getContent());
    }
}
