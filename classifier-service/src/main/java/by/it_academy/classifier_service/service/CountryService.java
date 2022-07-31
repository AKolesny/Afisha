package by.it_academy.classifier_service.service;

import by.it_academy.classifier_service.dao.api.ICountryDao;
import by.it_academy.classifier_service.dao.entity.Country;
import by.it_academy.classifier_service.dto.country.CountryDto;
import by.it_academy.classifier_service.mappers.CountryMapper;
import by.it_academy.classifier_service.service.api.ICountryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class CountryService implements ICountryService {

    private final ICountryDao dao;
    private final CountryMapper mapper;

    public CountryService(ICountryDao dao, CountryMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public Country save(CountryDto dto) {
        Country country = this.mapper.convertToCountry(dto);

        this.dao.save(country);

        return country;
    }

    @Override
    public Page<Country> getAll(PageRequest pageRequest) {
        return this.dao.findAll(pageRequest);
    }

    @Override
    public Country get(UUID uuid) {
        return this.dao.findById(uuid)
                .orElseThrow(() -> {throw new EntityNotFoundException();
        });
    }
}
