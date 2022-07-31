package by.it_academy.classifier_service.controller;

import by.it_academy.classifier_service.dto.category.CategoryDto;
import by.it_academy.classifier_service.dto.api.IMyPage;
import by.it_academy.classifier_service.dto.category.CategoryOutDto;
import by.it_academy.classifier_service.mappers.CategoryMapper;
import by.it_academy.classifier_service.service.api.ICategoryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/classifier/concert/category")
public class CategoryController {

    private final ICategoryService service;
    private final CategoryMapper mapper;

    public CategoryController(ICategoryService service, CategoryMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<CategoryOutDto> save(@RequestBody CategoryDto category) {
        CategoryOutDto dto = this.mapper.convertToDto(this.service.save(category));

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<CategoryOutDto> get(@PathVariable UUID uuid) {
        return ResponseEntity.ok(this.mapper.convertToDto(this.service.get(uuid)));
    }

    @GetMapping
    public ResponseEntity<IMyPage<CategoryOutDto>> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                          @RequestParam(value = "size", defaultValue = "25") Integer size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        IMyPage<CategoryOutDto> categories = this.mapper.convertToMyPage(this.service.getAll(pageRequest));

        return ResponseEntity.ok(categories);
    }
}
