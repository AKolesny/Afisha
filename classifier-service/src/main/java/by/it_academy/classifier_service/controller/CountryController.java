package by.it_academy.classifier_service.controller;

import by.it_academy.classifier_service.dao.entity.Country;
import by.it_academy.classifier_service.dto.CountryDto;
import by.it_academy.classifier_service.dto.IMyPage;
import by.it_academy.classifier_service.dto.MyPage;
import by.it_academy.classifier_service.service.api.ICountryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/classifier/country")
public class CountryController {

    private final ICountryService service;

    public CountryController(ICountryService service) {
        this.service = service;
    }

    @PostMapping
    public void save(@RequestBody CountryDto country) {
        this.service.save(country);
    }

    @GetMapping("/{uuid}")
    public Country get(@PathVariable UUID uuid) {
        return this.service.get(uuid);
    }

    @GetMapping
    public IMyPage<Country> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "25") Integer size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Country> countries = this.service.getAll(pageRequest);

        return new MyPage<>(countries.getNumber(), countries.getSize(),
                countries.getTotalPages(), countries.getTotalElements(), countries.isFirst(),
                countries.getNumberOfElements(), countries.isLast(), countries.getContent());
    }
}

