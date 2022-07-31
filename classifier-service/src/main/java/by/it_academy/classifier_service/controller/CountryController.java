package by.it_academy.classifier_service.controller;

import by.it_academy.classifier_service.dto.country.CountryDto;
import by.it_academy.classifier_service.dto.api.IMyPage;
import by.it_academy.classifier_service.dto.country.CountryOutDto;
import by.it_academy.classifier_service.mappers.CountryMapper;
import by.it_academy.classifier_service.service.api.ICountryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/classifier/country")
public class CountryController {

    private final ICountryService service;
    private final CountryMapper mapper;

    public CountryController(ICountryService service, CountryMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<CountryOutDto> save(@RequestBody CountryDto country) {
        CountryOutDto dto = this.mapper.convertToDto(this.service.save(country));;

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<CountryOutDto> get(@PathVariable UUID uuid) {
        return ResponseEntity.ok(this.mapper.convertToDto(this.service.get(uuid)));
    }

    @GetMapping
    public ResponseEntity<IMyPage<CountryOutDto>> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "25") Integer size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        IMyPage<CountryOutDto> countries = this.mapper.convertToMyPage(this.service.getAll(pageRequest));

        return ResponseEntity.ok(countries);
    }
}

