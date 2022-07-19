package by.it_academy.classifier_service.service;


import by.it_academy.classifier_service.dao.api.ICountryDao;
import by.it_academy.classifier_service.dao.entity.Country;
import by.it_academy.classifier_service.dto.CountryDto;
import by.it_academy.classifier_service.service.api.ICountryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;;
import java.util.UUID;

@Service
public class CountryService implements ICountryService {

    private final ICountryDao dao;

    public CountryService(ICountryDao dao) {
        this.dao = dao;
    }

    @Override
    public void save(CountryDto countryDto) {
        Country country = new Country();
        country.setUuid(UUID.randomUUID());
        country.setDtCreate(LocalDateTime.now());
        country.setDtUpdate(country.getDtUpdate());
        country.setTitle(countryDto.getTitle());
        country.setDescription(countryDto.getDescription());

        this.dao.save(country);
    }

    @Override
    public Page<Country> getAll(PageRequest pageRequest) {
        return this.dao.findAll(pageRequest);
    }

    @Override
    public Country get(UUID uuid) {
        return this.dao.findById(uuid)
                .orElseThrow(() -> {throw new IllegalArgumentException("Не нашли такого Country");
        });
    }
}
