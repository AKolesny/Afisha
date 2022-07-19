package by.it_academy.classifier_service.service.api;

import by.it_academy.classifier_service.dao.entity.Country;
import by.it_academy.classifier_service.dto.CountryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface ICountryService {

    void save(CountryDto countryDto);

    Page<Country> getAll(PageRequest pageRequest);

    Country get(UUID uuid);
}
